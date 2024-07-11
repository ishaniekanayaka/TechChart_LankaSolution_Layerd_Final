package lk.ijse.dep.repository;

public class SupplierRepo {
   /* public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier (supplierId, s_name, product, contact_num ,itemId) VALUES(?,?,?,?,?)";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getS_name());
        pstm.setObject(3, supplier.getProduct());
        pstm.setObject(4, supplier.getContact_num());
        pstm.setObject(5, supplier.getItemId());


        return pstm.executeUpdate() > 0;
    }*/

   /* public static Supplier searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM  WHERE  supplierId= ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String  s_name= resultSet.getString(2);
            String product = resultSet.getString(3);
            String  contact_num= resultSet.getString(4);
            String itemId = resultSet.getString(5);


            var supplier = new Supplier(supplierId, s_name, product, contact_num, itemId);

            return supplier;
        }

        return null;
    }*/
   /* public static boolean update(Supplier supplier) throws SQLException {

        String sql = "UPDATE Supplier SET  s_name = ?, product  = ?, contact_num =? WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getS_name());
        pstm.setObject(3, supplier.getProduct());
        pstm.setObject(4, supplier.getContact_num());
       // pstm.setObject(5, supplier.getItemId());



        return pstm.executeUpdate() > 0;
    }*/

   /* public static boolean update2(String supplierId,  String s_name, String product, String contact_num) throws SQLException {
        String sql = "UPDATE Supplier SET  s_name = ?, product  = ?, contact_num =? WHERE supplierId = ?";


        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, s_name);
        pstm.setObject(2, product);
        pstm.setObject(3, contact_num);
        pstm.setObject(4, supplierId);



        return pstm.executeUpdate() > 0;
    }*/

   /* public static boolean delete(String supplierId) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplierId);

        return pstm.executeUpdate() > 0;
    }*/

    /*public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String supplierId= resultSet.getString(1);
            String s_name = resultSet.getString(2);
            String product = resultSet.getString(3);
            String  contact_num= resultSet.getString(4);
            String itemId = resultSet.getString(5);


            Supplier supplier = new Supplier(supplierId, s_name, product, contact_num, itemId);
            supplierList.add(supplier);
        }
        return supplierList;
    }*/

    /*public static List<String> getIds() throws SQLException {
        String sql = "SELECT supplierId FROM Supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            idList.add(supplierId);
        }
        return idList;
    }*/

    /*public static String GetSupplierId() throws SQLException {
        String sql = "SELECT supplierId FROM Supplier ORDER BY supplierId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String supplierId= resultSet.getString(1);
            return supplierId;
        }
        return null;
    }*/
}