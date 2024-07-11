package lk.ijse.dep.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.entity.Order;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.OrderDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.model.PlaceOrderDTO;
import lk.ijse.dep.bo.custom.PurchaseOrderBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.OrderDAO;
import lk.ijse.dep.dao.custom.OrderDetailsDAO;
import lk.ijse.dep.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dep.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dep.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.repository.ItemRepo;
import lk.ijse.dep.repository.OrderDetailRepo;
import lk.ijse.dep.repository.OrderRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

   OrderDAO orderDAO = (OrderDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.ORDER);
   OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.ORDER_DETAILS);

   @Override
    public  boolean placeOrder(PlaceOrderDTO po) throws SQLException {
    Connection connection = DbConnection.getInstance().getConnection();
        try {
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
        try {

        boolean isOrderSaved = saveOrder(po.getOrder());

        if (isOrderSaved) {
            ItemDAO itemDAO =new ItemDAOImpl();
            boolean isQtyUpdated = itemDAO.updateList(po.getOdList());
            if (isQtyUpdated) {
                boolean isOrderDetailSaved = saveList( po.getOdList());

                if (isOrderDetailSaved) {
                    connection.commit();
                    return true;
                }
            }
        }

        connection.rollback();
        return false;
    } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
        connection.setAutoCommit(true);
    }
}

    @Override
    public String GetOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.GetOrderId();
    }
//orderId , date ,customerId , Total
    @Override
    public boolean saveOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(order.getOrderId(),order.getDate(),order.getCustomerId(),order.getTotal()));
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return orderDAO.getCodes();
    }

    @Override
    public OrderDTO SearchByOrderId(String orderId) throws SQLException, ClassNotFoundException {
        Order order = orderDAO.searchById(orderId);
        return new OrderDTO(order.getOrderId(),order.getDate(),order.getCustomerId(),order.getTotal());
    }

    @Override
    public String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException {
        return orderDAO.SearchByIdGetTotal(orderId);
    }

    @Override
    public List<String> getAllOrders() throws SQLException, ClassNotFoundException {
        return orderDAO.getAllOrders();
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return  orderDAO.generateNewID();
    }

    @Override
    public boolean saveList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDTO Od : odList) {

            boolean isSaved = orderDetailsDAO.save(new OrderDetailsDTO(Od.getItemId(),Od.getOrderId(),Od.getQty(),Od.getUnitPrice()));

            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

//OrderId,itemId,QTY,unitPrice
    @Override
    public boolean saveOrderDetails(OrderDetailsDTO od) throws SQLException, ClassNotFoundException {
       return orderDetailsDAO.save(new OrderDetailsDTO(od.getItemId(),od.getOrderId(),od.getQty(),od.getUnitPrice()));
    }

}
