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
import lk.ijse.dep.model.OrderDTO;
import lk.ijse.dep.model.ServiceDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.PurchaseOrderBO;
import lk.ijse.dep.bo.custom.ServiceBO;
import lk.ijse.dep.model.tm.ServiceTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceManagementForm {


    public AnchorPane rootNodeCusService;
    public TableView tblCustomerService;
    public TableColumn colServiceId;
    public TableColumn colContactNum;
    public TableColumn colDescription;
    public TextField txtServiceId;
    public TextField txtContact;
    public TextField txtDescription;
    public JFXComboBox cmbDescription;
    public JFXComboBox <String> cmbOrderId;
    public TableColumn colOrderId;

    ServiceBO serviceBO = (ServiceBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.SERVICE);
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.PURCHASE_ORDER);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       String Id = txtServiceId.getText();

       ServiceDTO service = serviceBO.searchByServiceId(Id);
       setCellValueFactory();
       loadAllCustomers();

       if (service != null){
           txtServiceId.setText(service.getCustomerServiceId());
           cmbOrderId.getValue();
           txtContact.setText(service.getContact_num());
           txtDescription.setText(service.getDescription());

            } else {
                new Alert(Alert.AlertType.ERROR, "Service Not Found").show();
            }

    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCusService.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String customerServiceId = txtServiceId.getText();
        String orderId = cmbOrderId.getValue();
        String contact_num = txtContact.getText();
        String description = (String) cmbDescription.getValue();

        if (isValid()){
        ServiceDTO service = new ServiceDTO(customerServiceId,orderId,contact_num, description);

        try {
            boolean isSaved = serviceBO.saveService(service);
            setCellValueFactory();
            loadAllCustomers();
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Service is Saved").show();

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
        String customerServiceId = txtServiceId.getText();
        String orderId = cmbOrderId.getValue();
        String contact_num = txtContact.getText();
        String description = (String) cmbDescription.getValue();
    if(isValid()){
        try {
            boolean isUpdated = serviceBO.updateService(new ServiceDTO(customerServiceId ,orderId, contact_num, description));
            setCellValueFactory();
            loadAllCustomers();
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "service  didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Validation Failed");
        alert.setContentText("Please fill in all fields correctly.");
        alert.showAndWait();
    }
    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerServiceId = txtServiceId.getText();


        try {
            boolean isDelete = serviceBO.deleteService(customerServiceId);
            setCellValueFactory();
            loadAllCustomers();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtServiceId.setText("");
        txtContact.setText("");
        cmbDescription.getValue();

    }

  public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        setDescription();
        setOrderId();
    }

    private void loadAllCustomers() {
        ObservableList<ServiceTm> objects = FXCollections.observableArrayList();

        try {
            List<ServiceDTO> serviceList = serviceBO.getAllService();
            for (ServiceDTO service : serviceList) {
                ServiceTm tm = new ServiceTm(
                        service.getCustomerServiceId(),
                        service.getOrderId(),
                        service.getContact_num(),
                        service.getDescription()
                );
                objects.add(tm);
            }
            tblCustomerService.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colServiceId.setCellValueFactory(new PropertyValueFactory<>("customerServiceId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("Contact_num"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }

    public void txtServiceIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDCS,txtServiceId);
    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT,txtContact);
    }

    public boolean isValid(){


        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDCS, txtServiceId);
        boolean contactValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtContact);
       // boolean descriptionValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.DESCRIPTION,txtDescription);

        return  idValid && contactValid ;
    }

    public void cmbOrderIdOnAction(ActionEvent actionEvent) {
        String  orderId = cmbOrderId.getValue();

        try {
            OrderDTO order = purchaseOrderBO.SearchByOrderId(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setDescription() {
        ObservableList<String> description = FXCollections.observableArrayList();
        cmbDescription.setValue("Broken");

        description.add("Broken");
        description.add("Burnt out");
        description.add("Not working");

        cmbDescription.setItems(description);
    }

    public void setOrderId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = purchaseOrderBO.getCodes();

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
}
