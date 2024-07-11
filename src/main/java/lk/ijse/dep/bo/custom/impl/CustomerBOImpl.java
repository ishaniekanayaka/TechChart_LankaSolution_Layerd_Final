package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Customer;
import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.bo.custom.CustomerBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.CustomerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.CUSTOMER);

    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(dto.getCustomerId(),dto.getC_name(),dto.getEmail(),dto.getContact_num(),dto.getAddress());
       return customerDAO.save(customer);
    }

    public boolean deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
       return customerDAO.delete(customerId);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(dto.getCustomerId(),dto.getC_name(),dto.getEmail(),dto.getContact_num(),dto.getAddress());
        return customerDAO.update(customer);
    }


    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customersDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getCustomerId(), customer.getC_name(),customer.getEmail(),customer.getContact_num(), customer.getAddress());
            customersDTOS.add(customerDTO);

        }

        return customersDTOS;
    }

    public CustomerDTO searchByCusId(String Id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(Id);
        return new CustomerDTO(customer.getCustomerId(),customer.getC_name(),customer.getEmail(),customer.getContact_num(),customer.getAddress());
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {
        return customerDAO.getId();
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDAO.getCurrentId();
    }

    public String generateNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }
}
