package lk.ijse.dep.bo.custom.impl;

import lk.ijse.dep.entity.Delivery;
import lk.ijse.dep.entity.Item;
import lk.ijse.dep.entity.Payment;
import lk.ijse.dep.model.DeliveryDTO;
import lk.ijse.dep.model.ItemDTO;
import lk.ijse.dep.model.PaymentDTO;
import lk.ijse.dep.bo.custom.PaymentBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.custom.PaymentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getdaoFactory().getDAO(DAOFactory.DaoTypes.PAYMENT);
//paymentId,amount,paymentDate,method,orderId
    @Override
    public boolean savePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(dto.getPaymentId(),dto.getAmount(),dto.getPaymentDate(),dto.getMethod(),dto.getOrderId()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(),dto.getAmount(),dto.getPaymentDate(),dto.getMethod(),dto.getOrderId()));
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException, ClassNotFoundException {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>() ;
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(payment.getPaymentId(), payment.getAmount(), payment.getPaymentDate(), payment.getMethod(), payment.getOrderId()));
        }
       return paymentDTOS;
    }

    @Override
    public PaymentDTO searchByPaymentId(String Id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.searchById(Id);
        return new PaymentDTO(payment.getPaymentId(), payment.getAmount(), payment.getPaymentDate(), payment.getMethod(), payment.getOrderId());
    }

    @Override
    public List<String> getPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getId();
    }

    @Override
    public String getCurrentPaymentId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentId();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }
}
