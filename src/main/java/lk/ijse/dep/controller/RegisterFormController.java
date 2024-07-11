package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.Util.Regex;
import lk.ijse.dep.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {

    public AnchorPane rootNodeRegister;
    public TextField txtUserId;
    public TextField txtPassword;
    public TextField txtUserName;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String userName =txtUserName.getText();

        String password = txtPassword.getText();
        if (isValied()) {
        try {
            boolean isSaved = saveUser(userId,userName,password);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Registered Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    private boolean saveUser(String userId, String userName, String password) throws SQLException {
        String sql = "INSERT INTO User VALUES (?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userId);
        pstm.setString(2,userName);
        pstm.setString(3,password);

        return pstm.executeUpdate() > 0;
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void txtUserPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.PASSWORD, txtPassword);
    }

    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtUserName);
    }

    public void txtUserIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.dep.Util.TextField.IDA, txtUserId);
    }


    public boolean isValied(){

        boolean nameValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.NAME, txtUserName);
        boolean idValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.IDA, txtUserId);
        boolean passValid = Regex.setTextColor(lk.ijse.dep.Util.TextField.PASSWORD, txtPassword);

        return nameValid && idValid && passValid;
    }
}
