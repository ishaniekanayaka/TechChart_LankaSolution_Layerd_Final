package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Service;
import lk.ijse.dep.model.ServiceDTO;
import lk.ijse.dep.bo.custom.ServiceBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.ServiceDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceBOImpl implements ServiceBO {
    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.SERVICE);
//customerServiceId,orderId, contact_num , description

    @Override
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.save(new Service(dto.getCustomerServiceId(),dto.getOrderId(),dto.getContact_num(),dto.getDescription()));
    }

    @Override
    public boolean deleteService(String id) throws SQLException, ClassNotFoundException {
       return serviceDAO.delete(id);
    }

    @Override
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(dto.getCustomerServiceId(),dto.getOrderId(),dto.getContact_num(),dto.getDescription()));
    }

    @Override
    public List<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException {
        List<Service> services = serviceDAO.getAll();
        List<ServiceDTO> serviceDTOS = new ArrayList<>();
        for (Service service : services) {
            serviceDTOS.add(new ServiceDTO(service.getCustomerServiceId(), service.getOrderId(), service.getContact_num(), service.getDescription()));
        }
        return serviceDTOS;
    }

    @Override
    public ServiceDTO searchByServiceId(String Id) throws SQLException, ClassNotFoundException {
        Service service = serviceDAO.searchById(Id);
        return new ServiceDTO(service.getCustomerServiceId(), service.getOrderId(), service.getContact_num(), service.getDescription());
    }

    @Override
    public List<String> getServiceId() throws SQLException, ClassNotFoundException {
       return serviceDAO.getId();
    }
}
