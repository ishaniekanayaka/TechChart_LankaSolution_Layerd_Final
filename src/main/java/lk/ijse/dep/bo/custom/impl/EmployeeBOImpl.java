package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Delivery;
import lk.ijse.dep.entity.Employee;
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.bo.custom.EmployeeBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.EmployeeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.EMPLOYEE);

    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
       return employeeDAO.save(new Employee(dto.getEmployeeId(),dto.getSalary(),dto.getE_name(),dto.getPosition(),dto.getContact_num(),dto.getEmail()));
    }

    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
       return employeeDAO.delete(employeeId);
    }

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmployeeId(),dto.getSalary(),dto.getE_name(),dto.getPosition(),dto.getContact_num(),dto.getEmail()));
    }

    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<Employee> employees= employeeDAO.getAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmployeeId(),employee.getSalary(),employee.getE_name(),employee.getPosition(),employee.getContact_num(),employee.getEmail());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getId();
    }

    public String getCurrentEmpId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentId();
    }

    public String generateNewEmpID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    public EmployeeDTO searchByEmpId(String Id) throws SQLException, ClassNotFoundException {

        Employee employee = employeeDAO.searchById(Id);
        return new EmployeeDTO(employee.getEmployeeId(), employee.getSalary(), employee.getE_name(), employee.getPosition(), employee.getContact_num(), employee.getEmail());
    }
}
