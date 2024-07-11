package lk.ijse.dep.dao;

import lk.ijse.dep.model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public boolean save(T dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(T dto) throws SQLException, ClassNotFoundException;

    public List<T> getAll() throws SQLException, ClassNotFoundException;

    public T searchById(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getId() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public List<String> getNames() throws SQLException, ClassNotFoundException;

    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;

    public T searchByName(String name) throws SQLException, ClassNotFoundException;

    public boolean saveList(List<T> odList) throws SQLException, ClassNotFoundException;

    public String GetOrderId() throws SQLException, ClassNotFoundException;

    public String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException;

    public List<String> getAllOrders() throws SQLException, ClassNotFoundException;

   public boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException;



}

