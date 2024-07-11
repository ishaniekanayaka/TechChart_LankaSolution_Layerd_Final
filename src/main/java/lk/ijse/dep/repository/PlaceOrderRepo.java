package lk.ijse.dep.repository;

import javafx.scene.control.Alert;
import lk.ijse.dep.model.PlaceOrderDTO;
import lk.ijse.dep.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dep.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dep.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
//    public  boolean placeOrder(PlaceOrderDTO po) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        try {
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//        try {
//            OrderDAOImpl orderDAO = new OrderDAOImpl();
//            boolean isOrderSaved = orderDAO.save(po.getOrder());
//
//            if (isOrderSaved) {
//                ItemDAOImpl itemDAO = new ItemDAOImpl();
//                boolean isQtyUpdated = itemDAO.updateList(po.getOdList());
//
//                if (isQtyUpdated) {
//                    OrderDetailsDAOImpl orderDetailsDAO = new OrderDetailsDAOImpl();
//                    boolean isOrderDetailSaved = orderDetailsDAO.saveList(po.getOdList());
//
//                    if (isOrderDetailSaved) {
//                        connection.commit();
//                        return true;
//                    }
//                }
//            }
//
//            connection.rollback();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
}
