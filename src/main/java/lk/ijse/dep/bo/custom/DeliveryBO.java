package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface DeliveryBO extends SuperBO {

    public boolean saveDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException;

    public DeliveryDTO searchByDeliveryId(String Id) throws SQLException, ClassNotFoundException;

    public List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException;

    public List<String> getDeliveryId() throws SQLException, ClassNotFoundException;

    public  boolean deleteDelivery(String deliveryId) throws SQLException, ClassNotFoundException;
}
