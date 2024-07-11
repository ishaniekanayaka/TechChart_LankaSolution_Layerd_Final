package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.ServiceDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface ServiceBO extends SuperBO {
    public boolean saveService(ServiceDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteService(String id) throws SQLException, ClassNotFoundException;

    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException;

    public List<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;

    public ServiceDTO searchByServiceId(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getServiceId() throws SQLException, ClassNotFoundException;
}
