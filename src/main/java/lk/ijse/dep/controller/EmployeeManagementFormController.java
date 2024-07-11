package lk.ijse.dep.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.EmployeeBO;
import lk.ijse.dep.model.tm.EmployeeTm;

import java.sql.SQLException;
import java.util.List;

public class EmployeeManagementFormController {



    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtPosition;
    public TextField txtContactNum;
    public TextField txtEmail;
    public TextField txtSalary;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colPosition;
    public TableColumn colContact_num;
    public TableColumn colEmail;
    public TableColumn colSalary;
    public TableView tblEmployeeManagement;
    public AnchorPane rootNoteEmployee;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.EMPLOYEE);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
      String id = txtEmployeeId.getText();

      EmployeeDTO employee = employeeBO.searchByEmpId(id);
      setCellValueFactory();
      loadAllCustomers();

      if (employee!= null){
                txtEmployeeId.setText(employee.getEmployeeId());
                txtSalary.setText(employee.getSalary());
                txtName.setText(employee.getE_name());
                txtPosition.setText(employee.getPosition());
                txtContactNum.setText(employee.getContact_num());
                txtEmail.setText(employee.getEmail());


            } else {
                new Alert(Alert.AlertType.INFORMATION, "Employee Not Found").show();
            }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        String employeeId = txtEmployeeId.getText();
        String salary = txtSalary.getText();
        String e_name = txtName.getText();
        String position = txtPosition.getText();
        String contact_num = txtContactNum.getText();
        String email = txtEmail.getText();

        if (isValid()){
            EmployeeDTO employee = new EmployeeDTO(employeeId, salary, e_name,position,contact_num,email);

            try {
                boolean isSaved = employeeBO.saveEmployee(employee);
                loadAllCustomers();
                setCellValueFactory();
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Employee is Saved").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
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

    private void clearFields() {
        txtEmployeeId.setText("");
        txtSalary.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtContactNum.setText("");
        txtEmail.setText("");
    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentEmployeeId();
    }

    private void loadAllCustomers() {
        ObservableList<EmployeeTm> objects = FXCollections.observableArrayList();

        try {
            List<EmployeeDTO> employeeList = employeeBO.getAllEmployee();
            for (EmployeeDTO employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getSalary(),
                        employee.getE_name(),
                        employee.getPosition(),
                        employee.getContact_num(),
                        employee.getEmail()
                );
                objects.add(tm);
            }
            tblEmployeeManagement.setItems(objects);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colName.setCellValueFactory(new PropertyValueFactory<>("E_name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        colContact_num.setCellValueFactory(new PropertyValueFactory<>("Contact_num"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String salary = txtSalary.getText();
        String e_name = txtName.getText();
        String position = txtPosition.getText();
        String contact_num = txtContactNum.getText();
        String email = txtEmail.getText();

        if (isValid()){
            try {
                boolean isUpdated = employeeBO.updateEmployee(new EmployeeDTO(employeeId,salary, e_name, position, contact_num, email));
                loadAllCustomers();
                setCellValueFactory();
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "employee  didnt updated!").show();
                }

            } catch (SQLException | ClassNotFoundException e) {

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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();


        try {
            boolean isDelete = employeeBO.deleteEmployee(employeeId);
            loadAllCustomers();
            setCellValueFactory();
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtName);
    }

    public void txtSalaryOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.DOUBLE, txtSalary);
    }

    public void txtContactNumOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT, txtContactNum);
    }

    public void txtPositionOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.POSITION, txtPosition);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.EMAIL, txtEmail);
    }

    public void txtEmployeeIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDE, txtEmployeeId);
    }

    public boolean isValid(){
        boolean nameValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtName);
        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDE, txtEmployeeId);
        boolean positionValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.POSITION, txtPosition);
        boolean salaryValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.DOUBLE, txtSalary);
        boolean contactValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.CONTACT,txtContactNum);
        boolean emailValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.EMAIL,txtEmail);

        return nameValid && idValid && positionValid && salaryValid && contactValid && emailValid ;
    }

    private String getCurrentEmployeeId() {
        try {
            String employeeId = employeeBO.getCurrentEmpId();

            String nextEmployeeId = generateNextAssestId();
            txtEmployeeId.setText(nextEmployeeId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public  String generateNextAssestId() throws SQLException, ClassNotFoundException {

        return employeeBO.generateNewEmpID();
    }

    public static String splitEmployeeId(String string) {
        if(string != null) {
            String[] strings = string.split("E0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "E00"+id;
            }else {
                if (length < 3){
                    return "E0"+id;
                }else {
                    return "E"+id;
                }
            }
        }
        return "E001";
    }

}
