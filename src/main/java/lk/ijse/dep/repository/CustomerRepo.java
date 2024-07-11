package lk.ijse.dep.repository;

import lk.ijse.dep.model.CustomerDTO;
import lk.ijse.dep.bo.BOFactory;
import lk.ijse.dep.bo.custom.CustomerBO;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepo {
}

  /*  static CustomerBO customerBO = (CustomerBO) BOFactory.getboFactory().getBO(BOFactory.BoTypes.CUSTOMER);

    public static void save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
       *//* String sql = "INSERT INTO Customer VALUES(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getEmail());
        pstm.setObject(4, customer.getContact_num());
        pstm.setObject(5, customer.getAddress());

        return pstm.executeUpdate() > 0;*//*

        customerBO.saveCustomer(new CustomerDTO(customer.getCustomerId(), customer.getC_name(), customer.getEmail(), customer.getContact_num(), customer.getAddress()));

    }

    public static   CustomerDTO searchById(String Id) throws SQLException, ClassNotFoundException {
        *//*String sql = "SELECT * FROM Customer WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );

        }

        return null;*//*

       return customerBO.searchByCusId(Id);
    }

*/
  /*  public static void update(CustomerDTO customer) throws SQLException {

        *//*String sql = "UPDATE Customer SET c_name = ?, email = ?, contact_num = ?, address = ? WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getEmail());
        pstm.setObject(4, customer.getContact_num());
        pstm.setObject(5, customer.getAddress());


        return pstm.executeUpdate() > 0;*//*

        customerBO.updateCustomer(new CustomerDTO(customer.getCustomerId(), customer.getC_name(), customer.getEmail(), customer.getContact_num(), customer.getAddress()));
    }
*/

   /* public static List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
       *//* String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String c_name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact_num = resultSet.getString(4);
            String address = resultSet.getString(5);


            Customer customer = new Customer(customerId, c_name, email, contact_num, address);
            cusList.add(customer);*//*

        return customerBO.getAllCustomers();

      *//*  return cusList;*//*
    }*/

  /*  public static List<String> getIds() throws SQLException, ClassNotFoundException {
       *//* String sql = "SELECT customerId FROM Customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            idList.add(customerId);
        }
        return idList;*//*

        return customerBO.getId();
    }*/

   /* public static void delete(String customerId) throws SQLException, ClassNotFoundException {
        *//*String sql = "DELETE FROM Customer WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customerId);

        return pstm.executeUpdate() > 0;*//*

       customerBO.deleteCustomer(customerId);

    }

 public static void update2(String customerId, String cName, String email, String contactNum, String address) throws SQLException, ClassNotFoundException {
       */ //String sql = "UPDATE Customer SET c_name = ?, email = ?, contact_num = ?, address = ? WHERE customerId = ?";
//
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setObject(1, cName);
//        pstm.setObject(2, email);
//        pstm.setObject(3, contactNum);
//        pstm.setObject(4, address);
//        pstm.setObject(5 ,customerId);
//
//        return pstm.executeUpdate() > 0;*/
//
     //customerBO.updateCustomer1(new CustomerDTO(dto.getCustomerId(),dto.getC_name(),dto.getEmail(),dto.getContact_num(),dto.getAddress()));



   /* public static String GetCustomerId() throws SQLException, ClassNotFoundException {
        *//*String sql = "SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String customerId = resultSet.getString(1);
            return customerId;
        }
        return null;*//*
        return customerBO.getCurrentId();
    }
    */



