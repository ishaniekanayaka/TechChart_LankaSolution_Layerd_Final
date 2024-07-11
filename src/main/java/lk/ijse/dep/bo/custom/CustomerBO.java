package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;

    //public void updateCustomer(CustomerDTO dto) throws SQLException;

    public boolean updateCustomer(CustomerDTO dto/*String customerId, String cName, String email, String contactNum, String address*/) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public CustomerDTO searchByCusId(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getId() throws SQLException, ClassNotFoundException;

    public String getCurrentId() throws SQLException, ClassNotFoundException;

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException;


}
