package lk.ijse.dep.bo.custom;

import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.bo.SuperBO;
import lk.ijse.dep.model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    public ItemDTO searchByItemId(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getItemId() throws SQLException, ClassNotFoundException;

    public  List<String> getItemNames() throws SQLException, ClassNotFoundException;

    public   boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;

    public  List<String> getCodes() throws SQLException, ClassNotFoundException;

    public  ItemDTO searchByItemName(String name) throws SQLException, ClassNotFoundException;

    public  boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException ;

    }
