package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.OrderDetailsDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
   public  boolean saveList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {
      for (OrderDetailsDTO Od : odList) {
          boolean isSaved = save(Od);
           if(!isSaved) {
               return false;
           }
        }
        return true;
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


    public  boolean save(OrderDetailsDTO od) throws SQLException, ClassNotFoundException {
        //return SQLUtil.execute("INSERT INTO Orders_detail(OrderId,itemId,QTY,unitPrice) VALUES(?, ?, ?, ?)",od.getOrderId(),od.getItemId(),od.getQty(),od.getUnitPrice());
       /* return SQLUtil.execute("INSERT INTO Orders_detail(QTY,unitPrice,OrderId,itemId) VALUES(?, ?, ?, ?)",
                od.getQty(), od.getUnitPrice(), od.getOrderId(), od.getItemId()
        );*/

       /* return SQLUtil.execute("INSERT INTO Orders_detail(OrderId,itemId,QTY,unitPrice) VALUES(?, ?, ?, ?)",
                od.getOrderId(), od.getItemId(), od.getQty(), od.getUnitPrice()
        );*/
           /* String oder = od.getOrderId();
        String oder2 = od.getItemId();
        int oder3 = od.getQty();
        double oder4 = od.getUnitPrice();

        System.out.println(oder);
        System.out.println(oder2);
        System.out.println(oder3);
        System.out.println(oder4);
        boolean save  = SQLUtil.execute("INSERT INTO Orders_detail VALUES(?,?,?,?)",od.getQty(),od.getUnitPrice(),od.getOrderId(),od.getItemId());
        System.out.println("Save = "+ save);

        return save;*/
        return SQLUtil.execute("INSERT INTO Orders_detail VALUES(?,?,?,?)",
                od.getQty(),od.getUnitPrice(), od.getOrderId(), od.getItemId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailsDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public OrderDetailsDTO searchById(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        return List.of();
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
    public OrderDetailsDTO searchByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }


}
