package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Supplier;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.model.SupplierDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.SupplierDAO;
import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.dep.controller.SupplierManagementFormController.splitSupplierId;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO Supplier (supplierId, s_name, product, contact_num ,itemId) VALUES(?,?,?,?,?)",dto.getSupplierId(), dto.getS_name(), dto.getProduct(),dto.getContact_num(),dto.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE supplierId = ?",id);
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier SET  s_name = ?, product  = ?, contact_num =? WHERE supplierId = ?",dto.getS_name(),dto.getProduct(),dto.getContact_num(), dto.getSupplierId());
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String supplierId= resultSet.getString(1);
            String s_name = resultSet.getString(2);
            String product = resultSet.getString(3);
            String  contact_num= resultSet.getString(4);
            String itemId = resultSet.getString(5);


            Supplier supplier = new Supplier(supplierId, s_name, product, contact_num, itemId);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    @Override
    public Supplier searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier WHERE  supplierId= ?",Id);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        }
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT supplierId FROM Supplier");
        while (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            idList.add(supplierId);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT supplierId FROM Supplier ORDER BY supplierId DESC LIMIT 1");

        if (resultSet.next()) {
            String supplierId= resultSet.getString(1);
            return supplierId;
        }
        return null;

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT SupplierId  FROM Supplier ORDER BY supplierId  DESC LIMIT 1";

        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitSupplierId(resultSet.getString(1));
        }
        return splitSupplierId(null);
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
    public Supplier searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveList(List<Supplier> odList) throws SQLException, ClassNotFoundException {
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
