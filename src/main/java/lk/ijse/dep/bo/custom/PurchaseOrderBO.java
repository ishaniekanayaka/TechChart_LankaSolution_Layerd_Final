package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.OrderDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.model.PlaceOrderDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {

   // public boolean placeOrder((OrderDTO order,OrderDetailsDTO orderDetails) throws SQLException, ClassNotFoundException;
     public  boolean placeOrder(PlaceOrderDTO po) throws SQLException;

    public String GetOrderId() throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;

    public OrderDTO SearchByOrderId(String orderId) throws SQLException, ClassNotFoundException;

    public String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException;

    public List<String> getAllOrders() throws SQLException, ClassNotFoundException;

    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public  boolean saveList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException;

    public  boolean saveOrderDetails(OrderDetailsDTO od) throws SQLException, ClassNotFoundException;
}

