package lk.ijse.dep.repository;

import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemRepo {
   /* public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO Items(itemId, i_name, qtyAvailable, itemPrice) VALUES(?,?,?,?)";
        // "INSERT INTO Items(itemId, i_name, qtyAvailable, itemPrice) VALUES(I001,Printer,100,50000.00)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getI_name());
        pstm.setObject(3, item.getQtyAvailable());
        pstm.setObject(4, item.getItemPrice());


        return pstm.executeUpdate() > 0;

    }*/

   /* public static Item searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM Items WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String  itemId= resultSet.getString(1);
            String  i_name= resultSet.getString(2);
            int qtyAvailable= Integer.parseInt(resultSet.getString(3));
            double  itemPrice= Double.parseDouble(resultSet.getString(4));

           Item item = new Item(itemId, i_name,qtyAvailable, itemPrice);

            return item;
        }

        return null;
    }
*/
    /*public static Item searchByName(String name) throws SQLException {
        String sql = "SELECT * FROM Items WHERE i_name = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, name);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String  itemId= resultSet.getString(1);
            String  i_name= resultSet.getString(2);
            int  qtyAvailable= Integer.parseInt(resultSet.getString(3));
            double itemPrice= Double.parseDouble(resultSet.getString(4));

            Item item = new Item(itemId, i_name,qtyAvailable, itemPrice);

            return item;
        }

        return null;
    }*/

  /*  public static boolean update(Item item) throws SQLException {

        String sql = "UPDATE Items SET  i_name= ?,  qtyAvailable= ?, itemPrice= ? WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, item.getItemId());
        pstm.setObject(2, item.getI_name());
        pstm.setObject(3, item.getQtyAvailable());
        pstm.setObject(4, item.getItemPrice());


        return pstm.executeUpdate() > 0;
    }*/

    public static boolean delete(String itemId) throws SQLException {
        String sql = "DELETE FROM Items WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, itemId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update2(String itemId, String i_name,  int qtyAvailable, double itemPrice) throws SQLException {
        String sql = "UPDATE Items SET  i_name= ?,  qtyAvailable= ?, itemPrice= ? WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, i_name);
        pstm.setObject(2, qtyAvailable);
        pstm.setObject(3, itemPrice);
        pstm.setObject(4 ,itemId);

        return pstm.executeUpdate() > 0;
    }
   /* public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM Items";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Item> itemList= new ArrayList<>();

        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String i_name = resultSet.getString(2);
            int qtyAvailable= Integer.parseInt(resultSet.getString(3));
            double itemPrice= Double.parseDouble(resultSet.getString(4));



            Item item = new Item(itemId, i_name, qtyAvailable,itemPrice);
            itemList.add(item);
        }
        return itemList;
    }*/

   /* public static List<String> getIds() throws SQLException {
        String sql = "SELECT itemId FROM Items";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String itemId= resultSet.getString(1);
            idList.add(itemId);
        }
        return idList;
    }*/

   /* public static List<String> getNames() throws SQLException {
        String sql = "SELECT i_name FROM Items";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> nameList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String itemId= resultSet.getString(1);
            nameList.add(itemId);
        }
        return nameList;
    }*/

    /*public static boolean update(List<OrderDetails> odList) throws SQLException {
        for (OrderDetails od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }*/

    /*private static boolean updateQty(String itemCode, int qty) throws SQLException {
        System.out.println(qty);
        String sql = "UPDATE Items SET qtyAvailable = (qtyAvailable - ?) WHERE itemId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;
    }*/

    /*public static List<String> getCodes() throws SQLException {
        String sql = "SELECT itemId FROM Items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> ItemId = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            ItemId.add(resultSet.getString(1));

        }
       return ItemId;
    }*/
}
