package lk.ijse.dep.repository;

import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.dao.custom.impl.DeliveryDAOImpl;
import lk.ijse.dep.entity.Delivery;

import java.sql.SQLException;
import java.util.List;

public class DeliveryRepo {

   /* public static void save(Delivery delivery) throws SQLException, ClassNotFoundException {
        *//*String sql = "INSERT INTO Delivery VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, delivery.getDeliveryId());
        pstm.setObject(2, delivery.getDeliveryDate());
        pstm.setObject(3, delivery.getStatus());

        return pstm.executeUpdate() > 0;*//*
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        deliveryDAO.save(new DeliveryDTO(delivery.getDeliveryId(), delivery.getDeliveryDate(), delivery.getStatus()));
    }*/

   /* public static boolean update(Delivery delivery) throws SQLException {

        *//*String sql = "UPDATE Delivery SET deliveryDate = ?, status = ? WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, delivery.getDeliveryId());
        pstm.setObject(2, delivery.getDeliveryDate());
        pstm.setObject(3, delivery.getStatus());

        return pstm.executeUpdate() > 0;*//*
    }*/

   /* public static boolean update2(String deliveryId, String deliveryDate, String status) throws SQLException {
        String sql = "UPDATE Delivery SET deliveryDate = ?, status = ?WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, deliveryDate);
        pstm.setObject(2, status);
        pstm.setObject(3, deliveryId);

        return pstm.executeUpdate() > 0;
    }*/

   /* public static Delivery searchById(String Id) throws SQLException {
       *//* String sql = "SELECT * FROM Delivery WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String deliveryId = resultSet.getString(1);
            String deliveryDate = resultSet.getString(2);
            String status = resultSet.getString(3);

            Delivery delivery = new Delivery (deliveryId, deliveryDate, status);

            return delivery;
        }
        return null;*//*
    }*/

   /* public static List<DeliveryDTO> getAll() throws SQLException {
        *//*String sql = "SELECT * FROM Delivery";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Delivery> deliveryList = new ArrayList<>();

        while (resultSet.next()) {
            String deliveryId = resultSet.getString(1);
            String deliveryDate = resultSet.getString(2);
            String status = resultSet.getString(3);

            Delivery delivery = new Delivery(deliveryId, deliveryDate, status);
            deliveryList.add(delivery);
        }
        return deliveryList;*//*
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return  deliveryDAO.getAllDelivery();
    }*/

   /* public static List<DeliveryDTO> getIds() throws SQLException, ClassNotFoundException {
       *//* String sql = "SELECT deliveryId FROM Delivery";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String deliveryId= resultSet.getString(1);
            idList.add(deliveryId);
        }
        return idList;*//*
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.getAll();
    }*/
   /* public static boolean delete(String deliveryId) throws SQLException {
        String sql = "DELETE FROM Delivery WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, deliveryId);

        return pstm.executeUpdate() > 0;
    }*/
}
