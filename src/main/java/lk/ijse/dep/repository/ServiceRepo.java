package lk.ijse.dep.repository;

import lk.ijse.dep.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepo {
    /*public static boolean save(Service service) throws SQLException {
        String sql = "INSERT INTO CustomerService (customerServiceId,orderId, contact_num , description ) VALUES(?,?,?,?)";



        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, service.getCustomerServiceId());
        pstm.setObject(2,service.getOrderId());
        pstm.setObject(3, service.getContact_num());
        pstm.setObject(4, service.getDescription());


        return pstm.executeUpdate() > 0;
    }*/

   /* public static Service searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM  WHERE customerServiceId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String customerServiceId = resultSet.getString(1);
            String orderId = resultSet.getString(2);
            String contact_num = resultSet.getString(2);
            String description = resultSet.getString(3);

            Service service = new Service(customerServiceId, orderId, contact_num, description);

            return service;
        }

        return null;
    }
*/
   /* public static boolean update(Service service) throws SQLException {

       *//* String sql = "UPDATE CustomerService SET contact_num = ?, description = ? WHERE customerServiceId = ?";
        //UPDATE CustomerSerive SET contact_num ='0767888979', description 'Service Done' WHERE = customerServiceId = 'CS001';

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, service.getCustomerServiceId());
        pstm.setObject(2, service.getContact_num());
        pstm.setObject(3, service.getDescription());



        return pstm.executeUpdate() > 0;*//*
    }
*/
   /*public static boolean delete(String customerServiceId) throws SQLException {
       *//* String sql = "DELETE FROM CustomerService WHERE  customerServiceId= ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customerServiceId);

        return pstm.executeUpdate() > 0;*//*
    }*/

    /*public static boolean update2(String customerServiceId,  String contact_num, String description) throws SQLException {
        String sql = "UPDATE CustomerService SET contact_num = ?, description = ? WHERE customerServiceId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, contact_num);
        pstm.setObject(2, description);
        pstm.setObject(3, customerServiceId);


        return pstm.executeUpdate() > 0;
    }*/

   /* public static List<Service> getAll() throws SQLException {
        String sql = "SELECT * FROM CustomerService";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Service> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String customerServiceId = resultSet.getString(1);
            String orderId = resultSet.getString(2);
            String contact_num = resultSet.getString(3);
            String description = resultSet.getString(4);


            Service service = new Service(customerServiceId,orderId, contact_num, description);
            cusList.add(service);
        }
        return cusList;
    }*/

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customerServiceId  FROM CustomerSerive";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String customerServiceId= resultSet.getString(1);
            idList.add(customerServiceId);
        }
        return idList;
    }
}
