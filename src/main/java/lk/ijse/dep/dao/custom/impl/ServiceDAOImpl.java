package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Service;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.model.ServiceDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.ServiceDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    @Override
    public boolean save(Service dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO CustomerService (customerServiceId,orderId, contact_num , description ) VALUES(?,?,?,?)",dto.getCustomerServiceId(),dto.getOrderId(), dto.getContact_num(),dto.getDescription());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM CustomerService WHERE  customerServiceId= ?",id);
    }

    @Override
    public boolean update(Service dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE CustomerService SET contact_num = ?, description = ? WHERE customerServiceId = ?", dto.getContact_num(), dto.getDescription(), dto.getCustomerServiceId());
    }

    @Override
    public List<Service> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM CustomerService");

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
    }

    @Override
    public Service searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM CustomerService  WHERE customerServiceId = ?",Id);
        if (resultSet.next()) {
            return new Service(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }

        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT customerServiceId  FROM CustomerService");
        while (resultSet.next()) {
            String customerServiceId= resultSet.getString(1);
            idList.add(customerServiceId);
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
    public Service searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveList(List<Service> odList) throws SQLException, ClassNotFoundException {
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
