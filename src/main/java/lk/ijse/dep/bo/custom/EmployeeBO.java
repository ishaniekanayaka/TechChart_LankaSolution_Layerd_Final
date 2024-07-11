package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public boolean saveEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException;

    public String getCurrentEmpId() throws SQLException, ClassNotFoundException;

    public String generateNewEmpID() throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchByEmpId(String Id) throws SQLException, ClassNotFoundException;

    }
