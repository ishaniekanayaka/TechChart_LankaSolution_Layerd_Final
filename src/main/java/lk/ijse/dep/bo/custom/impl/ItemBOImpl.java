package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dep.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.dep.entity.Delivery;
import lk.ijse.dep.entity.Employee;
import lk.ijse.dep.entity.Item;
import lk.ijse.dep.entity.OrderDetails;
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.bo.custom.ItemBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.model.OrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.ITEM);
//itemId, i_name, qtyAvailable, itemPrice
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getItemId(),dto.getI_name(),dto.getQtyAvailable(),dto.getItemPrice()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getItemId(),dto.getI_name(),dto.getQtyAvailable(),dto.getItemPrice()));
    }

    @Override
    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        List<Item> items = itemDAO.getAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getItemId(), item.getI_name(), item.getQtyAvailable(), item.getItemPrice()));
        }
        return itemDTOS;
    }

    @Override
    public ItemDTO searchByItemId(String Id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchById(Id);
        return new ItemDTO(item.getItemId(),item.getI_name(),item.getQtyAvailable(),item.getItemPrice());
    }

    @Override
    public List<String> getItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getId();
    }

    @Override
    public List<String> getItemNames() throws SQLException, ClassNotFoundException {
        return itemDAO.getNames();
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
       return itemDAO.updateQty(itemCode,qty);
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getCodes();
    }

    @Override
    public ItemDTO searchByItemName(String name) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchByName(name);
        return new ItemDTO(item.getItemId(),item.getI_name(),item.getQtyAvailable(),item.getItemPrice());
    }

    @Override
    public boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDTO od : odList) {

            boolean isUpdateQty = itemDAO.updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

}
