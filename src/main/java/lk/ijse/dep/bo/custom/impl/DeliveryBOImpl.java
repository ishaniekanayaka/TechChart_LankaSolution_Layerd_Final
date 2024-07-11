package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Customer;
import lk.ijse.dep.entity.Delivery;
import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.bo.custom.DeliveryBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.DeliveryDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {

    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.DELIVERY);

    @Override
    public boolean saveDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.save(new Delivery(dto.getDeliveryId(),dto.getDeliveryDate(),dto.getStatus()));
    }

    @Override
    public boolean updateDelivery(DeliveryDTO dto) throws SQLException, ClassNotFoundException {
        return deliveryDAO.update(new Delivery(dto.getDeliveryId(),dto.getDeliveryDate(),dto.getStatus()));
    }

    @Override
    public DeliveryDTO searchByDeliveryId(String Id) throws SQLException, ClassNotFoundException {
        Delivery delivery = deliveryDAO.searchById(Id);
        return new DeliveryDTO(delivery.getDeliveryId(),delivery.getDeliveryDate(),delivery.getStatus());
    }

    @Override
    public List<DeliveryDTO> getAllDelivery() throws SQLException, ClassNotFoundException {
        List<Delivery> deliveries = deliveryDAO.getAll();
        List<DeliveryDTO> deliveryDTOS = new ArrayList<>() ;
        for (Delivery delivery : deliveries) {
            deliveryDTOS.add(new DeliveryDTO(delivery.getDeliveryId(), delivery.getDeliveryDate(), delivery.getStatus()));
        }
        return deliveryDTOS;
    }

    @Override
    public List<String> getDeliveryId() throws SQLException, ClassNotFoundException {
        return deliveryDAO.getId();
    }

    @Override
    public boolean deleteDelivery(String deliveryId) throws SQLException, ClassNotFoundException {
        return deliveryDAO.delete(deliveryId);
    }
}
