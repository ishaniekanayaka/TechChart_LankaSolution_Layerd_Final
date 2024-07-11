package lk.ijse.dep.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.CustomerBO;
import lk.ijse.dep.model.tm.CustomerTm;

import java.sql.SQLException;
import java.util.List;

public class CustomerManagementFormController {

    CustomerBO customerBO = (CustomerBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.CUSTOMER);

    // public TableView tblCustomer;
    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private AnchorPane rootNodeCus;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;


    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerContact.setText("");
        txtCustomerAddress.setText("");

    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentCustomerId();


    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> objects = FXCollections.observableArrayList();

        try {

            List<CustomerDTO> customerList =customerBO.getAllCustomers();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getC_name(),
                        customer.getEmail(),
                        customer.getContact_num(),
                        customer.getAddress()
                );

                objects.add(tm);
            }

            tblCustomer.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();

        try {

            boolean isDelete = customerBO.deleteCustomer(customerId);
            setCellValueFactory();
            loadAllCustomers();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String c_name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String contact_num = txtCustomerContact.getText();
        String address = txtCustomerAddress.getText();

        if (isValid()) {
            CustomerDTO customer = new CustomerDTO(customerId, c_name, email, contact_num, address);

            try {

                boolean isSaved =customerBO.saveCustomer(customer);/* CustomerRepo.save(customer);*/
                setCellValueFactory();
                loadAllCustomers();
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
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
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String customerId = txtCustomerId.getText();
        String c_name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String contact_num = txtCustomerContact.getText();
        String address = txtCustomerAddress.getText();
        System.out.println(customerId);


        if (isValid()) {
            try {

                boolean isUpdated = customerBO.updateCustomer(new CustomerDTO(customerId,c_name, email, contact_num, address));
                setCellValueFactory();
                loadAllCustomers();
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "customer  didnt updated!").show();
                }

            } catch (SQLException e) {

                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtCustomerId.getText();


        CustomerDTO customer = customerBO.searchByCusId(id);
        setCellValueFactory();
        loadAllCustomers();
        if (customer != null) {
            txtCustomerId.setText(customer.getCustomerId());
            txtCustomerName.setText(customer.getC_name());
            txtCustomerEmail.setText(customer.getEmail());
            txtCustomerContact.setText(customer.getContact_num());
            txtCustomerAddress.setText(customer.getAddress());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();

        }
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDC, txtCustomerId);

    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtCustomerName);
    }

    public void txtCustomerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.ADDRESS, txtCustomerAddress);
    }

    public void txtCustomerEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.EMAIL, txtCustomerEmail);

    }

    public void txtContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtCustomerContact);

    }

    public boolean isValid() {

        boolean nameValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtCustomerName);
        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDC, txtCustomerId);
        boolean addressValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.ADDRESS, txtCustomerAddress);
        boolean contactValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtCustomerContact);
        boolean emailValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.EMAIL, txtCustomerEmail);

        return nameValid && idValid && addressValid && contactValid && emailValid;
    }

    private String getCurrentCustomerId() {
        try {

            String customerId = customerBO.getCurrentId();

            String nextCustomerId = generateNextAssestId();
            txtCustomerId.setText(nextCustomerId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  String generateNextAssestId() throws SQLException, ClassNotFoundException {

        return customerBO.generateNewCustomerID();

    }

    public static String splitCustomerId(String string) {
        if (string != null) {
            String[] strings = string.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "C00" + id;
            } else {
                if (length < 3) {
                    return "C0" + id;
                } else {
                    return "C" + id;
                }
            }
        }
        return "C001";
    }
}
