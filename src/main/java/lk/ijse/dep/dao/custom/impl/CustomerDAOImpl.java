package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Customer;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.CustomerDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.dep.controller.CustomerManagementFormController.splitCustomerId;


public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer VALUES(?,?,?,?,?)",dto.getCustomerId(),dto.getC_name(),dto.getEmail(),dto.getContact_num(),dto.getAddress());
    }

    @Override
   public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
         return SQLUtil.execute("DELETE FROM Customer WHERE customerId = ?", customerId);
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET c_name = ?, email = ?, contact_num = ?, address = ? WHERE customerId = ?", dto.getC_name(),dto.getEmail(),dto.getContact_num(), dto.getAddress(),dto.getCustomerId());
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String c_name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact_num = resultSet.getString(4);
            String address = resultSet.getString(5);


            Customer customer = new Customer(customerId, c_name, email, contact_num, address);
            cusList.add(customer);
        }
        return cusList;
    }

    @Override
    public Customer searchById(String Id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM Customer WHERE customerId = ?",Id);
        if (resultSet.next()) {
            return new Customer(
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

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM Customer");
        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            idList.add(customerId);
        }
        return idList;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1");

        if (resultSet.next()) {
            String customerId = resultSet.getString(1);
            return customerId;
        }
        return null;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CustomerId FROM Customer ORDER BY customerId DESC LIMIT 1");

        if (resultSet.next()) {
            return splitCustomerId(resultSet.getString(1));
        }
        return splitCustomerId(null);
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
    public Customer searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public boolean saveList(List<Customer> odList) throws SQLException, ClassNotFoundException {
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