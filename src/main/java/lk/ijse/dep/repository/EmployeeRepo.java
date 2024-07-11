package lk.ijse.dep.repository;

import lk.ijse.dep.model.EmployeeDTO;
import lk.ijse.dep.dao.custom.impl.EmployeeDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class EmployeeRepo {
//    public static void save(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
//       /* String sql = "INSERT INTO Employee (employeeId, salary,e_name,position,contact_num,email) VALUES(?,?,?,?,?,?)";
//
//
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setObject(1, employee.getEmployeeId());
//        pstm.setObject(2, employee.getSalary());
//        pstm.setObject(3, employee.getE_name());
//        pstm.setObject(4, employee.getPosition());
//        pstm.setObject(5, employee.getContact_num());
//        pstm.setObject(6, employee.getEmail());
//
//
//        return pstm.executeUpdate() > 0;*/
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        employeeDAO.save(new EmployeeDTO(employee.getEmployeeId(),employee.getSalary(),employee.getE_name(),employee.getPosition(),employee.getContact_num(),employee.getEmail()));
//    }



   /*public static Employee searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM  WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String employeeId = resultSet.getString(1);
           // String userId= resultSet.getString(2);
            String salary = resultSet.getString(2);
            String e_name = resultSet.getString(3);
            String position = resultSet.getString(4);
            String contact_num = resultSet.getString(5);
            String email= resultSet.getString(6);


            Employee employee = new Employee(employeeId, salary, e_name, position, contact_num, email);

            return employee;
        }

        return null;
    }*/
   /* public static boolean update(Employee employee) throws SQLException {

        String sql = "UPDATE Employee SET salary = ?, e_name = ?, position = ?, contact_num = ?, email = ?WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getSalary());
        pstm.setObject(3, employee.getE_name());
        pstm.setObject(4, employee.getPosition());
        pstm.setObject(5, employee.getContact_num());
        pstm.setObject(6, employee.getEmail());



        return pstm.executeUpdate() > 0;
    }*/

//    public static void delete(String employeeId) throws SQLException, ClassNotFoundException {
//        /*String sql = "DELETE FROM Employee WHERE employeeId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, employeeId);
//
//        return pstm.executeUpdate() > 0;*/
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        employeeDAO.delete(employeeId);
//    }

    /*public static void update2(EmployeeDTO dto) throws SQLException {
        *//*String sql = "UPDATE Employee SET  salary = ?, e_name = ?, position = ?, contact_num = ?, email = ?WHERE employeeId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, salary);
        pstm.setObject(2, e_name);
        pstm.setObject(3, position);
        pstm.setObject(4 ,contact_num);
        pstm.setObject(5 ,email);
        pstm.setObject(6, employeeId);

        return pstm.executeUpdate() > 0;*//*
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
         employeeDAO.updateEmployee(new EmployeeDTO(dto.getEmployeeId(), dto.getSalary(), dto.getE_name(), dto.getPosition(), dto.getContact_num(), dto.getEmail()));
    }*/

//    public static List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
//        /*String sql = "SELECT * FROM Employee";
//
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery(sql);
//
//        List<Employee> cusList = new ArrayList<>();
//
//        while (resultSet.next()) {
//            String employeeId = resultSet.getString(1);
//            String salary = resultSet.getString(3);
//            String e_name = resultSet.getString(4);
//            String position = resultSet.getString(5);
//            String contact_num = resultSet.getString(6);
//            String email = resultSet.getString(7);
//
//
//            Employee employee = new Employee(employeeId, salary, e_name, position, contact_num, email);
//            cusList.add(employee);*/
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        return employeeDAO.getAll();
//
//    }
//
//    public static List<String> getIds() throws SQLException, ClassNotFoundException {
//       /* String sql = "SELECT employeeId FROM Employee";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        List<String> idList = new ArrayList<>();
//
//        ResultSet resultSet = pstm.executeQuery();
//        while (resultSet.next()) {
//            String employeeId= resultSet.getString(1);
//            idList.add(employeeId);
//        }
//        return idList;*/
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        return employeeDAO.getId();
//    }
//
//    public static String GetEmployeeId() throws SQLException, ClassNotFoundException {
//       /* String sql = "SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1";
//        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        if (resultSet.next()) {
//            String employeeId = resultSet.getString(1);
//            return employeeId;
//        }
//        return null;*/
//        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//        return employeeDAO.getCurrentId();
//    }
}
