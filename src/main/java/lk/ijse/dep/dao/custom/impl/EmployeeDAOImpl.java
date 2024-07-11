package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.entity.Employee;
import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.model.OrderDetailsDTO;
import lk.ijse.dep.dao.SQLUtil;
import lk.ijse.dep.dao.custom.EmployeeDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.dep.controller.EmployeeManagementFormController.splitEmployeeId;

public class EmployeeDAOImpl implements EmployeeDAO {

    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee (employeeId, salary,e_name,position,contact_num,email) VALUES(?,?,?,?,?,?)", dto.getEmployeeId(), dto.getSalary(), dto.getE_name(), dto.getPosition(), dto.getContact_num(), dto.getEmail());
    }

    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE employeeId = ?", employeeId);
    }

    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employee SET  salary = ?, e_name = ?, position = ?, contact_num = ?, email = ?WHERE employeeId = ?", dto.getSalary(), dto.getE_name(), dto.getPosition(), dto.getContact_num(), dto.getEmail(), dto.getEmployeeId());
    }

    public List<Employee> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");

        List<Employee> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String salary = resultSet.getString(3);
            String e_name = resultSet.getString(4);
            String position = resultSet.getString(5);
            String contact_num = resultSet.getString(6);
            String email = resultSet.getString(7);


            Employee employee = new Employee(employeeId, salary, e_name, position, contact_num, email);
            cusList.add(employee);
        }
        return cusList;
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT employeeId FROM Employee");
        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            idList.add(employeeId);
        }
        return idList;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1");

        if (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT EmployeeId  FROM Employee ORDER BY employeeId  DESC LIMIT 1");
        if (resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public Employee searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public boolean saveList(List<Employee> odList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String GetOrderId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String SearchByIdGetTotal(String orderId) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public List<String> getAllOrders() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean updateList(List<OrderDetailsDTO> odList) throws SQLException, ClassNotFoundException {
        return false;
    }


    public Employee searchById(String Id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE employeeId =?",Id);
        if (resultSet.next()) {
            return new Employee(
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );

        }
        return null;
    }
}