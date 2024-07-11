package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Item;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Items(itemId, i_name, qtyAvailable, itemPrice) VALUES(?,?,?,?)",dto.getItemId(),dto.getI_name(),dto.getQtyAvailable(),dto.getItemPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Items WHERE itemId = ?", id);
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Items SET  i_name= ?,  qtyAvailable= ?, itemPrice= ? WHERE itemId = ?",dto.getI_name(), dto.getQtyAvailable(), dto.getItemPrice(), dto.getItemId());
    }

    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Items");

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
    }

    @Override
    public Item searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Items WHERE itemId = ?",Id);
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT itemId FROM Items");
        while (resultSet.next()) {
            String itemId= resultSet.getString(1);
            idList.add(itemId);
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

    public  List<String> getNames() throws SQLException, ClassNotFoundException {

        List<String> nameList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT i_name FROM Items");
        while (resultSet.next()) {
            String itemId= resultSet.getString(1);
            nameList.add(itemId);
        }
        return nameList;
    }

    public  boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Items SET qtyAvailable = (qtyAvailable - ?) WHERE itemId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;

       // return SQLUtil.execute("UPDATE Items SET qtyAvailable = (qtyAvailable - ?) WHERE itemId = ?",itemCode, qty);
    }

    public  List<String> getCodes() throws SQLException, ClassNotFoundException {

        List<String> ItemId = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT itemId FROM Items");
        while (resultSet.next()){
            ItemId.add(resultSet.getString(1));

        }
        return ItemId;
    }

    public  Item searchByName(String name) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Items WHERE i_name = ?",name);
        if (resultSet.next()) {
            String  itemId= resultSet.getString(1);
            String  i_name= resultSet.getString(2);
            int  qtyAvailable= Integer.parseInt(resultSet.getString(3));
            double itemPrice= Double.parseDouble(resultSet.getString(4));

            Item item = new Item(itemId, i_name,qtyAvailable, itemPrice);

            return item;
        }

        return null;
    }

    @Override
    public boolean saveList(List<Item> odList) throws SQLException, ClassNotFoundException {
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


    public  boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailsDTO od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

}
