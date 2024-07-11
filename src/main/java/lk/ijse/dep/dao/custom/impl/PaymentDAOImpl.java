package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Payment;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.model.PaymentDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.PaymentDAO;
import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.dep.controller.PaymentFormController.splitOrderId;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment (paymentId,amount,paymentDate,method,orderId  )VALUES(?,?, ?, ?, ?)",dto.getPaymentId(), dto.getAmount(),dto.getPaymentDate(),dto.getMethod(),dto.getOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Payment WHERE paymentId = ?",id);
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Payment SET amount = ?, paymentDate = ?, method = ? WHERE paymentId = ?", dto.getAmount(), dto.getPaymentDate(),dto.getMethod(), dto.getPaymentId());
    }

    @Override
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment");

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String paymentDate = resultSet.getString(3);
            String method = resultSet.getString(4);
            String orderId = resultSet.getString(5);


            Payment payment = new Payment(paymentId, amount, paymentDate, method,orderId);
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment WHERE paymentId = ?",Id);
        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String paymentDate = resultSet.getString(3);
            String method = resultSet.getString(4);
            String orderId = resultSet.getString(5);

            Payment payment = new Payment(paymentId, amount, paymentDate, method,orderId);

            return payment;
        }
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute( "SELECT paymentId FROM Payment");
        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            idList.add(paymentId);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1");

        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Payment searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveList(List<Payment> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String GetOrderId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllOrders() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

}
