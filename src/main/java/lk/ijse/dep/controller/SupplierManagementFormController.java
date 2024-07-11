package lk.ijse.dep.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.model.SupplierDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.ItemBO;
import lk.ijse.dep.bo.custom.SupplierBO;
import lk.ijse.dep.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dep.model.tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierManagementFormController {


    public AnchorPane rootNodeSupplier;
    public TableView tblSupplier;
    public TableColumn colSupplierId;
    public TableColumn colName;
    public TableColumn colProduct;
    public TableColumn colContact;
    public TextField txtSupplierId;
    public TextField txtName;
    public TextField txtProduct;
    public TextField txtContact;
    public JFXComboBox cmbItemId;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.SUPPLIER);
    ItemBO itemBO = (ItemBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.ITEM);

    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String supplierId = txtSupplierId.getText();
        String  s_name= txtName.getText();
        String product = txtProduct.getText();
        String contact_num = txtContact.getText();
        String itemId = (String) cmbItemId.getValue();

        if (isValid()){
        SupplierDTO supplier = new SupplierDTO(supplierId, s_name, product, contact_num, itemId);
            setCellValueFactory();
            loadAllCustomers();
        try {
            boolean isSaved = supplierBO.saveSupplier(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Supplier is Saved").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String supplierId = txtSupplierId.getText();
        String  s_name = txtName.getText();
        String product = txtProduct.getText();
        String  contact_num = txtContact.getText();
        String itemId = (String) cmbItemId.getValue();

        if (isValid()){
        try {
            boolean isUpdated = supplierBO.updateSupplier(new SupplierDTO(supplierId, s_name, product, contact_num,itemId));
            setCellValueFactory();
            loadAllCustomers();
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Supplier  didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    public  void  initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentSupplierId();
        setItemId();
    }

    private void loadAllCustomers() {
        ObservableList<SupplierTm> objects = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = supplierBO.getAllSupplier();
            for (SupplierDTO supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getS_name(),
                        supplier.getProduct(),
                        supplier.getContact_num()

                );

                objects.add(tm);
            }

            tblSupplier.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_num"));

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String supplierId = txtSupplierId.getText();


        try {
            boolean isDelete = supplierBO.deleteSupplier(supplierId);
            setCellValueFactory();
            loadAllCustomers();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtSupplierId.setText("");
        txtName.setText("");
        txtProduct.setText("");
        txtContact.setText("");
        cmbItemId.getValue();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeSupplier.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Id = txtSupplierId.getText();

        SupplierDTO supplier = supplierBO.searchBySupplierId(Id);
            setCellValueFactory();
            loadAllCustomers();

            if (supplier!=null){
                txtSupplierId.setText(supplier.getSupplierId());
                txtName.setText(supplier.getS_name());
                txtProduct.setText(supplier.getProduct());
                txtContact.setText(supplier.getContact_num());

            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
            }
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtContact);
    }

    public void txtProductOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.PRODUCT, txtProduct);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtName);
    }

    public void txtSupplierIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDS, txtSupplierId);
    }

    public boolean isValid(){

        boolean nameValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtName);
        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDS, txtSupplierId);
        boolean productValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.PRODUCT, txtProduct);
        boolean contactValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtContact);

        return nameValid && idValid && productValid && contactValid  ;
    }

    private void getCurrentSupplierId()  {
        try {
            String supplierId = supplierBO.getCurrentSupplierId();

            String nextSupplierId = generateNextAssestId();
            txtSupplierId.setText(nextSupplierId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public  String generateNextAssestId() throws SQLException, ClassNotFoundException {

        return supplierBO.generateNewSupplierID();
    }

    public static String splitSupplierId(String string) {
        if(string != null) {
            String[] strings = string.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }

    public void cmbItemIdOnAction(ActionEvent actionEvent) {
        String  itemId = (String) cmbItemId.getValue();

        try {
            ItemDTO item = itemBO.searchByItemName(itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setItemId() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ItemDAOImpl itemDAO = new ItemDAOImpl();
            List<String> idList = itemDAO.getCodes();

            for (String itemId : idList){
                obList.add(itemId);
            }
           cmbItemId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

