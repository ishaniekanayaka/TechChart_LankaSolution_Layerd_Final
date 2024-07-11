package lk.ijse.dep.controller;

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
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.DeliveryBO;
import lk.ijse.dep.model.tm.DeliveryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DeliveryManagementFormController {
    public TextField txtDeliveryId;

    public TextField txtStatus;

    public TableColumn colDeliveryId;
    public TableColumn colStatus;
    public AnchorPane rootNodeCusDelivery;
    public TableView tblCustomerPayment;
    public TableColumn colDate;
    public TextField txtDate;
    public TableView tblCustomerDelivery;

    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.DELIVERY);

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCusDelivery.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    public  void  initialize(){
        setCellValueFactory();
        loadAllCustomers();
        SetDate();
    }

    private void loadAllCustomers() {
        ObservableList<DeliveryTm> objects = FXCollections.observableArrayList();

        try {
            List<DeliveryDTO> deliveryList = deliveryBO.getAllDelivery();
            for (DeliveryDTO delivery : deliveryList) {
                DeliveryTm tm = new DeliveryTm(
                        delivery.getDeliveryId(),
                        delivery.getDeliveryDate(),
                        delivery.getStatus()
                );


                objects.add(tm);
            }

            tblCustomerDelivery.setItems(objects);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("DeliveryId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("DeliveryDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

    }



    public void btnDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String deliveryId = txtDeliveryId.getText();


        try {
            boolean isDelete = deliveryBO.deleteDelivery(deliveryId);
            setCellValueFactory();
            loadAllCustomers();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String deliveryId= txtDeliveryId.getText();
        String deliveryDate = txtDate.getText();
        String status = txtStatus.getText();

        if (isValid()){
        try {
            boolean isUpdated =deliveryBO.updateDelivery(new DeliveryDTO(deliveryId, deliveryDate, status));
            setCellValueFactory();
            loadAllCustomers();
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery update!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Delivery didn't update!").show();

            }
        }catch (SQLException | ClassNotFoundException e){
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String deliveryId = txtDeliveryId.getText();
        String deliveryDate = txtDate.getText();
        String status = txtStatus.getText();

        if (isValid()){
        DeliveryDTO delivery = new DeliveryDTO(deliveryId, deliveryDate, status);
            setCellValueFactory();
            loadAllCustomers();
        try {
            boolean isSaved = deliveryBO.saveDelivery(delivery);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Saved!").show();
                clearFields();
            }
        }catch (SQLException | ClassNotFoundException e){
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
        txtDeliveryId.setText("");
        txtDate.setText("");
        txtStatus.setText("");

    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtDeliveryId.getText();

        DeliveryDTO delivery = deliveryBO.searchByDeliveryId(id);
        if (delivery != null) {
            txtDeliveryId.setText(delivery.getDeliveryId());
            txtDate.setText(delivery.getDeliveryDate());
            txtStatus.setText(delivery.getStatus());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "delivery not found!").show();

        }
    }

    public void txtDeliveryIdOnKeyRelease(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDD,txtDeliveryId);
    }

    public void txtDateOnKeyRelease(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.DATE,txtDate);
    }

    public void txtStatusOnKeyRelease(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.STATUS,txtStatus);
    }

    public boolean isValid(){

        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDD, txtDeliveryId);
        boolean dateValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.DATE, txtDate);
        boolean statusValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.STATUS, txtStatus);

        return  idValid && dateValid && statusValid ;
    }
    private void SetDate() {
        LocalDate Date = LocalDate.now();
        txtDate.setText(String.valueOf(Date));
    }
}
