package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Delivery;
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.DeliveryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {

    public boolean save(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Delivery VALUES(?, ?, ?)",dto.getDeliveryId(), dto.getDeliveryDate(), dto.getStatus());
    }

    public boolean update(Delivery dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Delivery SET deliveryDate = ?, status = ? WHERE deliveryId = ?", dto.getDeliveryDate(), dto.getStatus(), dto.getDeliveryId());
    }

    public Delivery searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Delivery WHERE deliveryId = ?",Id);
        if (resultSet.next()) {
           return new Delivery(
                   resultSet.getString(1),
                   resultSet.getString(2),
                   resultSet.getString(3)
           );
        }
        return null;
    }

    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Delivery");

        List<Delivery> deliveryList = new ArrayList<>();

        while (resultSet.next()) {
            String deliveryId = resultSet.getString(1);
            String deliveryDate = resultSet.getString(2);
            String status = resultSet.getString(3);

            Delivery delivery = new Delivery(deliveryId, deliveryDate, status);
            deliveryList.add(delivery);
        }
        return deliveryList;
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT deliveryId FROM Delivery");
        while (resultSet.next()) {
            String deliveryId= resultSet.getString(1);
            idList.add(deliveryId);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
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

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Delivery searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public boolean saveList(List<Delivery> odList) throws SQLException, ClassNotFoundException {
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


    public  boolean delete(String deliveryId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Delivery WHERE deliveryId = ?",deliveryId);
    }
}
