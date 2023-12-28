package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.PaymentBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.PaymentDAO;
import lk.RoyalGatesHotels.dto.PaymentDTO;
import lk.RoyalGatesHotels.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public double generateTotValue() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateTotValue();
    }

    @Override
    public List<String> getGIds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getGIds();
    }

    @Override
    public List<String> getOIds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getOIds();
    }

    @Override
    public List<String> getHIds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getHIds();
    }

    @Override
    public List<String> getRds() throws SQLException, ClassNotFoundException {
        return paymentDAO.getRds();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return paymentDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getPaymentId(),dto.getReservationId(),dto.getTime(),dto.getDate(),dto.getOrderId(),dto.getCustomerId(),dto.getAmount()));
    }

    @Override
    public boolean update(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(),dto.getReservationId(),dto.getTime(),dto.getDate(),dto.getOrderId(),dto.getCustomerId(),dto.getAmount()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PaymentDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.setFields(id);
        return new PaymentDTO(payment.getPaymentId(),payment.getReservationId(),payment.getTime(),payment.getDate(),payment.getOrderId(),payment.getCustomerId(),payment.getAmount());
    }
}
