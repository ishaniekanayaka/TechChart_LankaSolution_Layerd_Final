package lk.ijse.dep.repository;

import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
   /* public static String GetOrderId() throws SQLException {
        String sql = "SELECT orderId FROM Orders ORDER BY OrderId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }*/

    /*public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO Orders ( orderId , date ,customerId , Total) VALUES (?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, order.getOrderId());
        pstm.setString(2, order.getDate());

        pstm.setString(3, order.getCustomerId());
        pstm.setString(4, order.getTotal());

        //pstm.setDate(3, Date.valueOf(order.getDate()));

        return pstm.executeUpdate() > 0;
    }
*/
   /* public static List<String> getCodes() throws SQLException {
        String sql = "SELECT orderId FROM Orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> OrderId = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            OrderId.add(resultSet.getString(1));

        }
        return OrderId;
    }*/


    public static String SearchByIdGetTotal(String orderId) throws SQLException {

        String sql = "SELECT Total FROM Orders WHERE orderId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, orderId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String total = resultSet.getString(1);

            return total;
        }
            return null;
        }

    public static List<String> getAllOrders() throws SQLException {
        String sql = "SELECT orderId FROM Orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> orderIds = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }
}
