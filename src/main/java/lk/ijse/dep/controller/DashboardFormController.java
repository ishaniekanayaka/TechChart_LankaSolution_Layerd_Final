package lk.ijse.dep.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {

    public AnchorPane ChildRootNode;
    public AnchorPane PerenetRootNode;
    public Label lblTodayCount;
    public Label lblTotalCount;
    public Label lblDate;
    public Label lblTime;
    private int orderCount;


    public void btnCustomerOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/customerManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);

    }

    public void btnCustomerServiceOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/customerService_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);

    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/payment_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);

    }

    public void btnDeliveryOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/deliveryManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/supplierManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/itemManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OrderManagement_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);
    }


    public void btnRegistrationOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/register_form.fxml"));
        Parent PerenetRootNode = null;
        try {
            PerenetRootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRootNode.getChildren().clear();
        ChildRootNode.getChildren().add(PerenetRootNode);

        /*Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");
        stage.show();*/
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
       /* Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.ChildRootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();*/
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.PerenetRootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    private void SetDate() {
        LocalDate Date = LocalDate.now();
        lblDate.setText(String.valueOf(Date));
    }

    private void setLocalTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME.ofPattern("HH:mm:ss"); // Define format
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    LocalTime timeInSriLanka = LocalTime.now(); // Get current time
                    String formattedTime = timeInSriLanka.format(formatter); // Format time
                    lblTime.setText(formattedTime); // Set formatted time to label
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline


    }

    public void initialize() {
        SetDate();
        setLocalTime();
        TotalOrders();
    }

  private void TotalOrders() {
       // int orderCount = 0;
        try {
            orderCount = getOrderCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            //new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setOrderCount(orderCount);

    }

    private void setOrderCount(int orderCount) {
        lblTotalCount.setText(String.valueOf(orderCount));

    }

    private int getOrderCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS orderCount FROM Orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("orderCount");
        }

       /* Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("orderCount");
        }*/

       return 0;


       /*private void TodayOrders () {

            try {
                todayOrderCount = getTodayOrderCount();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            setTodayOrderCount(todayOrderCount);
        }

        private void setTodayOrderCount ( int todayOrderCount){
            lblTodayOrders.setText(String.valueOf(todayOrderCount));

        }

        private int getTodayOrderCount () throws SQLException {
            String sql = "SELECT COUNT(*) AS todayOrderCount FROM orders WHERE DATE(orderDate) = CURRENT_DATE";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("todayOrderCount");
            }

            return 0;

        }*/
    }
}
