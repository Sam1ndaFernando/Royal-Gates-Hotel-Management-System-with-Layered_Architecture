package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.PaymentDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallMaintenanceDTO;
import lk.RoyalGatesHotels.dto.PaymentDTO;
import lk.RoyalGatesHotels.entity.Payment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT paymentId FROM payment ORDER BY paymentId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("P%04d", newNum);
            return newId;
        }
        return "P0001";
    }

    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO payment (paymentId, reservation_id, time, date, oder_id, customer_id, qty) VALUES(?,?,?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getPaymentId(), entity.getReservationId(), entity.getTime(), entity.getDate(), entity.getOrderId(), entity.getCustomerId(), entity.getQty());
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        String sql ="UPDATE payment SET time=?, date=?, oder_id=?, customer_id=?, qty=? WHERE paymentId=?";
        return SQLUtill.execute(sql, entity.getTime(), entity.getDate(), entity.getOrderId(), entity.getCustomerId(), entity.getQty(), entity.getPaymentId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM payment WHERE paymentId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Payment setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM payment WHERE paymentId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String paymentId = resultSet.getString("paymentId");
            String reservationId = resultSet.getString("reservation_id");
            String time = resultSet.getString("time");
            String date = resultSet.getString("date");
            String orderId = resultSet.getString("oder_id");
            String customerId = resultSet.getString("customer_id");
            String qty = resultSet.getString("qty");

            return new Payment(paymentId, reservationId, time, date, orderId, customerId, qty);
        }
        return null;
    }

    @Override
    public double generateTotValue() throws SQLException, ClassNotFoundException {
        double totalAmount = 0;
        String sql = "SELECT SUM(amount) AS totalAmount FROM payment";
        ResultSet resultSet = SQLUtill.execute(sql);
        if (resultSet.next()) {
            totalAmount = resultSet.getDouble("totalAmount");
        }
        return totalAmount;
    }

    @Override
    public List<String> getGIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT customerId FROM customer";
        List<String> gIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            gIds.add(resultSet.getString(1));
        }
        return gIds;
    }

    @Override
    public List<String> getOIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT oder_id FROM meal_oders";
        List<String> oIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            oIds.add(resultSet.getString(1));
        }
        return oIds;
    }

    @Override
    public List<String> getHIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT reservationId FROM hallreservationdetail";
        List<String> hIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hIds.add(resultSet.getString(1));
        }
        return hIds;
    }

    @Override
    public List<String> getRds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT reservation_id FROM roomreservationdetail";
        List<String> rIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            rIds.add(resultSet.getString(1));
        }
        return rIds;
    }
}
