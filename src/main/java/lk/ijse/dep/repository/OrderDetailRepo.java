package lk.ijse.dep.repository;

public class OrderDetailRepo {
    /*public static boolean save(List<OrderDetails> odList) throws SQLException {
        for (OrderDetails Od : odList) {
            boolean isSaved = save(Od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    private static boolean save( OrderDetails od) throws SQLException {
        String sql = "INSERT INTO Orders_detail(OrderId,itemId,QTY,unitPrice) VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);


        pstm.setString(1, od.getOrderId());
        pstm.setString(2, od.getItemId());
        pstm.setInt(3,  od.getQty());
        pstm.setDouble(4, od.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }*/
}
