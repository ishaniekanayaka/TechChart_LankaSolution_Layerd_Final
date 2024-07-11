package lk.ijse.dep.repository;

public class PaymentRepo {
   /* public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO Payment (paymentId,amount,paymentDate,method,orderId  )VALUES(?,?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPaymentId());
        pstm.setObject(2, payment.getAmount());
        pstm.setObject(3, payment.getPaymentDate());
        pstm.setObject(4, payment.getMethod());
        pstm.setObject(5,payment.getOrderId());

        return pstm.executeUpdate() > 0;

    }*/

    /*public static boolean update(Payment payment) throws SQLException {

        String sql = "UPDATE Payment SET amount = ?, paymentDate = ?, method = ? WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, payment.getPaymentId());
        pstm.setObject(2, payment.getAmount());
        pstm.setObject(3, payment.getPaymentDate());
        pstm.setObject(4, payment.getMethod());

        return pstm.executeUpdate() > 0;
    }
*/
    /*public static boolean update2(String paymentId, String amount, String paymentDate, String method) throws SQLException {
        String sql = "UPDATE Payment SET amount = ?, paymentDate = ?, method = ? WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, amount);
        pstm.setObject(2, paymentDate);
        pstm.setObject(3, method);
        pstm.setObject(4, paymentId);

        return pstm.executeUpdate() > 0;
    }*/

    /*public static Payment searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM Payment WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String paymentDate = resultSet.getString(3);
            String method = resultSet.getString(4);
            String orderId = resultSet.getString(5);

            Payment payment = new Payment(paymentId, amount, paymentDate, method,orderId);

            return payment;
        }
        return null;
    }*/

   /* public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM Payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String amount = resultSet.getString(2);
            String paymentDate = resultSet.getString(3);
            String method = resultSet.getString(4);
            String orderId = resultSet.getString(5);


            Payment payment = new Payment(paymentId, amount, paymentDate, method,orderId);
            paymentList.add(payment);
        }
        return paymentList;
    }*/

   /* public static List<String> getIds() throws SQLException {
        String sql = "SELECT paymentId FROM Payment";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            idList.add(paymentId);
        }
        return idList;
    }*/
    /*public static boolean delete(String paymentId) throws SQLException {
        String sql = "DELETE FROM Payment WHERE paymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, paymentId);

        return pstm.executeUpdate() > 0;
    }*/

  /*  public static String GetPaymentId() throws SQLException {
        String sql = "SELECT paymentId FROM Payment ORDER BY paymentId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;
    }*/
}



