package lk.ijse.dep.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.model.PaymentDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.PaymentBO;
import lk.ijse.dep.bo.custom.PurchaseOrderBO;
import lk.ijse.dep.model.tm.PaymentTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentFormController {
    public AnchorPane rootNodeCusPayment;

    public TextField txtPaymentId;

    public TextField txtMethod;
    public TextField txtDate;

    public TableColumn colAmount;
    public TableColumn colDate;
    public TableColumn colMethod;

    public TableView tblCustomerPayment;
    public TableColumn colPaymentId;
    public JFXComboBox cmbMethod;
    public JFXComboBox<String> cmbOrderId;
    @FXML
    private Label lblTotal;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.PAYMENT);
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.PURCHASE_ORDER);

    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String paymentId = txtPaymentId.getText();
        String amount = lblTotal.getText();
        String paymentDate = txtDate.getText();
        String method = (String) cmbMethod.getValue();
        String orderId = (String) cmbOrderId.getValue();

        if (isValid()){
         PaymentDTO payment = new PaymentDTO(paymentId, amount, paymentDate, method,orderId);

         try {
             boolean isSaved = paymentBO.savePayment(payment);
             setCellValueFactory();
             loadAllCustomers();
             if (isSaved) {
                 new Alert(Alert.AlertType.CONFIRMATION, "Payment Saved!").show();
                 clearFields();
             }
             }catch (SQLException e){
             throw new RuntimeException(e);
         }
    }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    private void clearFields() {
        txtPaymentId.setText("");
        lblTotal.setText("");
        txtDate.setText("");
        cmbMethod.getValue();

    }

    public  void  initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllCustomers();
        setMethod();
       // setOrderId();
        setOrdersId();
        SetDate();
        getCurrentPaymentId();
    }

    private void loadAllCustomers()  {
        ObservableList<PaymentTm> objects = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> paymentList = paymentBO.getAllPayment();
            for (PaymentDTO payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getAmount(),
                        payment.getPaymentDate(),
                        payment.getMethod()
                       // payment.getOrderId()
                );


                objects.add(tm);
            }

            tblCustomerPayment.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("PaymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("Method"));

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String paymentId = txtPaymentId.getText();


        try {
            boolean isDelete = paymentBO.deletePayment(paymentId);
            setCellValueFactory();
            loadAllCustomers();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String paymentId = txtPaymentId.getText();
        String amount = lblTotal.getText();
        String paymentDate = txtDate.getText();
        String method = (String) cmbMethod.getValue();
        String orderId = (String) cmbOrderId.getValue();

        if (isValid()){
        try {
            boolean isUpdated = paymentBO.updatePayment(new PaymentDTO(paymentId, amount, paymentDate, method,orderId));
            setCellValueFactory();
            loadAllCustomers();
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment update!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "payment didn't update!").show();

            }
        }catch (SQLException e){
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




    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtPaymentId.getText();

        PaymentDTO payment = paymentBO.searchByPaymentId(id);
        if (payment != null) {
           txtPaymentId.setText(payment.getPaymentId());
           lblTotal.setText(payment.getAmount());
           txtDate.setText(payment.getPaymentDate());
           cmbMethod.getValue();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();

        }
    }

    public void txtPaymentIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDP,txtPaymentId);
    }

    public void txtDateOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.DATE, txtDate);
    }

    public boolean isValid(){

        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDP, txtPaymentId);
        //boolean amountValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.AMOUNT ,txtAmount);
        boolean dateValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.DATE, txtDate);
        //boolean methodValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.METHOD, txtMethod);

        return  idValid && dateValid ;
    }

    public void cmbMethodOnAction(ActionEvent actionEvent) {

    }

    private void setMethod() {
        ObservableList<String> method = FXCollections.observableArrayList();
        cmbMethod.setValue("Cash");

       method.add("Cash");
       method.add("Card");

       cmbMethod.setItems(method);
   }

    public void setOrdersId() {
        ObservableList<String> OrderIdList = FXCollections.observableArrayList();

        try{
            List<String> orderList = purchaseOrderBO.getAllOrders();
            for (String order : orderList) {
                OrderIdList.add(order);
            }
            cmbOrderId.setItems(OrderIdList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbOrdeIdOnAction(ActionEvent actionEvent) {
        setTotal();
    }
    public void setTotal(){
        String  orderId = cmbOrderId.getValue();

        try {
            String  total = purchaseOrderBO.SearchByIdGetTotal(orderId);

            lblTotal.setText(total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOrderId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {

            List<String> idList =purchaseOrderBO.getAllOrders();

            for (String orderId : idList){
                obList.add(orderId);
            }
            cmbOrderId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void SetDate(){
        LocalDate Date = LocalDate.now();
        txtDate.setText(String.valueOf(Date));
    }

    private void getCurrentPaymentId() throws ClassNotFoundException {
        try {
            String paymentId = paymentBO.getCurrentPaymentId();

            String nextPaymentID = generateNextAssestId();
            txtPaymentId.setText(nextPaymentID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  String generateNextAssestId() throws SQLException, ClassNotFoundException {

        return paymentBO.generateNewID();
    }

    public static String splitOrderId(String string) {
        if (string != null) {
            String[] strings = string.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "P00" + id;
            } else {
                if (length < 3) {
                    return "P0" + id;
                } else {
                    return "P" + id;
                }
            }
        }
        return "P001";
    }
}
