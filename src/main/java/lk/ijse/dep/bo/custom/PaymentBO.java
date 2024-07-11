package lk.ijse.dep.bo.custom;

import lk.ijse.dep.model.PaymentDTO;
import lk.ijse.dep.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public List<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException;

    public PaymentDTO searchByPaymentId(String Id) throws SQLException, ClassNotFoundException;

    public List<String> getPaymentId() throws SQLException, ClassNotFoundException;

    public String getCurrentPaymentId() throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;
}
