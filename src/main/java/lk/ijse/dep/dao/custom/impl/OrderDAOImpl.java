package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Order;
import lk.ijse.dep.model.OrderDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.OrderDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.dep.controller.OrderManagementFormController.splitOrderId;


public class OrderDAOImpl implements OrderDAO {

    public  String GetOrderId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM Orders ORDER BY OrderId DESC LIMIT 1");

        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public  boolean save(Order order) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO Orders ( orderId , date ,customerId , Total) VALUES (?, ?, ?, ?)",order.getOrderId(),order.getDate(),order.getCustomerId(),order.getTotal());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }


    @Override
    public Order searchById(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  List<String> getCodes() throws SQLException, ClassNotFoundException {

        List<String> OrderId = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute( "SELECT orderId FROM Orders");
        while (resultSet.next()){
            OrderId.add(resultSet.getString(1));

        }
        return OrderId;
    }

    @Override
    public Order searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveList(List<Order> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    public OrderDTO SearchById(String orderId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Orders WHERE orderId = ?",orderId);

        if (resultSet.next()){
            return new OrderDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public  String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT Total FROM Orders WHERE orderId = ?",orderId);
        if (resultSet.next()) {
            String total = resultSet.getString(1);

            return total;
        }
        return null;
    }

    public  List<String> getAllOrders() throws SQLException, ClassNotFoundException {

        List<String> orderIds = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM Orders");
        while (resultSet.next()) {
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }

    @Override
    public boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {
        return false;
    }


    public  String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1");
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
}
