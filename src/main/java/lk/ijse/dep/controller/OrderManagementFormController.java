package lk.ijse.dep.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.CustomerBO;
import lk.ijse.dep.bo.custom.ItemBO;
import lk.ijse.dep.bo.custom.PurchaseOrderBO;
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.*;
import lk.ijse.dep.model.tm.CartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Double.parseDouble;

public class OrderManagementFormController {

    public AnchorPane rootNodeOrder;
    @FXML
    public Label lblOrderId;
    @FXML
    public Label lblCustomerName;
    @FXML
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbItemName;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colTotalPrice;

    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private Label lblItemId;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblQuentityOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<CartTm> tblOrderCart;

    @FXML
    private TextField txtQTY;

    private static int idCounter = 0;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    //OrderDAOImpl orderDAO = new OrderDAOImpl();
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.PURCHASE_ORDER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.CUSTOMER);
    ItemBO itemBO = (ItemBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.ITEM);

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/customerManagement_form.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Customer Form");
        stage.show();
    }

    public void initialize() {
        setCellValueFactory();
        getCurrentOrderId();
        SetDate();
        setCustomerId();
        setItemName();
    }

    private void setCellValueFactory() {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("ItemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("TotalAmount"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("Action"));

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String ItemId = lblItemId.getText();
        String ItemName = cmbItemName.getValue();
        double UnitPrice = parseDouble(lblUnitPrice.getText());
        int QTY = Integer.parseInt(txtQTY.getText());
        double TotalPrice = QTY * UnitPrice;
        JFXButton Action = new JFXButton("Remove");
        Action.setCursor(Cursor.HAND);
        int qtyOnHand = Integer.parseInt(lblQuentityOnHand.getText());
        if (qtyOnHand >= QTY){

            Action.setOnAction((e) -> {
                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove", yes, no).showAndWait();
                if (type.orElse(no) == yes) {
                    int selectIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                    obList.remove(selectIndex);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                }

            });
            if (!obList.isEmpty()) {
                for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                    if (ItemId.equals(colItemID.getCellData(i))) {


                        QTY = QTY + (int) colQTY.getCellData(i);
                        TotalPrice += QTY * UnitPrice;

                        obList.get(i).setQTY(QTY);
                        obList.get(i).setTotalAmount(TotalPrice);

                        tblOrderCart.refresh();

                        calculateNetTotal();
                        return;
                    }
                }
            }
            CartTm tm = new CartTm(ItemId, ItemName, UnitPrice, QTY, TotalPrice, Action);
            obList.add(tm);

            tblOrderCart.setItems(obList);
            calculateNetTotal();
            // txtQTY.setText("");
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "ewwww gana wadi yakow!").show();
        }

    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            System.out.println(colTotalPrice.getCellData(i));
            netTotal = netTotal + (double) colTotalPrice.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, JRException, ClassNotFoundException {
        String orderId = lblOrderId.getText();
        String orderDate = lblOrderDate.getText();
        String customerId = cmbCustomerID.getValue();
        String total = lblNetTotal.getText();

        if (isValid()) {
            var order = new OrderDTO(orderId, orderDate, customerId,total);

            List<OrderDetailsDTO> odList = new ArrayList<>();

            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                CartTm tm = obList.get(i);
                OrderDetailsDTO orderDetails = new OrderDetailsDTO(

                        tm.getItemId(),
                        orderId,
                        tm.getQTY(),
                        tm.getUnitPrice());
                odList.add(orderDetails);
            }
            PlaceOrderDTO po = new PlaceOrderDTO(order, odList);
            boolean isPlaceOrder = purchaseOrderBO.placeOrder(po);

            if (isPlaceOrder) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order placed").show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Order placed Unsuccessful").show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        setCustomerId();

        String customerId = cmbCustomerID.getValue();

        try {
            CustomerDTO customer = customerBO.searchByCusId(customerId);
            if (customer != null) {
                lblCustomerName.setText(customer.getC_name());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCustomerId() {
        ObservableList<String> custIdList = FXCollections.observableArrayList();

        try {
            List<String> idList = customerBO.getId();
            for (String id : idList) {
                custIdList.add(id);
            }

            cmbCustomerID.setItems(custIdList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void cmbItemNameOnAction(ActionEvent event) {
        String name = cmbItemName.getValue();

        try {
            ItemDTO item = itemBO.searchByItemName(name);
            if (item != null) {
                lblItemId.setText(item.getItemId());
                lblUnitPrice.setText(String.valueOf(item.getItemPrice()));
                lblQuentityOnHand.setText(String.valueOf(item.getQtyAvailable()));
            }
            txtQTY.requestFocus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setItemName() {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        try {
            List<String> itemList = itemBO.getItemNames();
            for (String item : itemList) {
                oblist.add(item);
            }
            cmbItemName.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void SetDate() {
        LocalDate Date = LocalDate.now();
        lblOrderDate.setText(String.valueOf(Date));
    }

    private void getCurrentOrderId() {
        try {

            String orderId = purchaseOrderBO.GetOrderId();

            String nextOrderId = generateNextAssestId();
            lblOrderId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public  String generateNextAssestId() throws SQLException, ClassNotFoundException {

        return purchaseOrderBO.generateNewOrderId();
    }

    public static String splitOrderId(String string) {
        if (string != null) {
            String[] strings = string.split("O0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "O00" + id;
            } else {
                if (length < 3) {
                    return "O0" + id;
                } else {
                    return "O" + id;
                }
            }
        }
        return "O001";
    }

    public void txtQTYOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.QTY, txtQTY);

    }

    public boolean isValid() {

        boolean qtyValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.QTY, txtQTY);

        return qtyValid;
    }

    public void btnBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {


        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/jmmbBlank_A4.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", lblOrderId.getText());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
    }


}