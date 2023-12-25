package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.PaymentBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.PaymentDAO;
import lk.RoyalGatesHotels.dto.Payment;

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
    public boolean add(Payment dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getPaymentId(), (String) dto.getReservationId(),dto.getTime(),dto.getDate(),dto.getOrderId(),dto.getCustomerId(), (Integer) dto.getQty()));
    }

    @Override
    public boolean update(Payment dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPaymentId(), (String) dto.getReservationId(),dto.getTime(),dto.getDate(),dto.getOrderId(),dto.getCustomerId(), (Integer) dto.getQty()));
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
    public Payment setFields(String id) throws SQLException, ClassNotFoundException {
        Payment payment = paymentDAO.setFields(id);
        return new Payment(payment.getPaymentId(), (String) payment.getReservationId(),payment.getTime(),payment.getDate(),payment.getOrderId(),payment.getCustomerId(), (Integer) payment.getQty());
    }
}
