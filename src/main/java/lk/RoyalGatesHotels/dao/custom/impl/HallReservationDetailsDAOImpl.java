package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.HallReservationDAO;
import lk.RoyalGatesHotels.dao.custom.HallReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.HallMaintenanceDTO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.entity.HallReservation;
import lk.RoyalGatesHotels.entity.HallReservationDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallReservationDetailsDAOImpl implements HallReservationDetailsDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(HallReservationDetails entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO hallreservationdetail(hallNumber, customer_id, reservationId, check_out_date, check_in_date) VALUES(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getHall_number(), entity.getCustomer_id(), entity.getReservation_id(), entity.getCheck_out_date(), entity.getCheck_in_date());
    }

    @Override
    public boolean update(HallReservationDetails entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE hallreservationdetail SET hallNumber=?, customer_id=?, check_out_date=?, check_in_date=? WHERE reservationId=?";
        return SQLUtill.execute(sql, entity.getHall_number(), entity.getCustomer_id(), entity.getReservation_id(), entity.getCheck_out_date(), entity.getCheck_in_date());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM hallreservationdetail WHERE reservationId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HallReservationDetails setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM hallreservationdetail WHERE reservationId=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String hallNumber = resultSet.getString("hallNumber");
            String customerId = resultSet.getString("customer_id");
            String hallReservationId = resultSet.getString("reservationId");
            String checkInDate = String.valueOf(resultSet.getDate("check_in_date"));
            String checkOutDate = String.valueOf(resultSet.getDate("check_out_date"));
            return new HallReservationDetails(hallNumber, customerId, hallReservationId, checkInDate, checkOutDate);
        }
        return null;
    }

    @Override
    public List<HallReservationDetails> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM hallreservationdetail";
        List<HallReservationDetails> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new HallReservationDetails(
                    resultSet.getString("hallNumber"),
                    resultSet.getString("customer_id"),
                    resultSet.getString("reservationId"),
                    resultSet.getString("check_out_date"),
                    resultSet.getString("check_in_date")
            ));
        }
        return data;
    }

    @Override
    public boolean removeH(String hallReservationId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM hallreservationdetail WHERE reservationId = ?";
        return SQLUtill.execute(sql, hallReservationId);
    }

    @Override
    public String getHall(String value) throws SQLException, ClassNotFoundException {
        String hallId;
        String sql = "SELECT * FROM hallreservationdetail WHERE reservationId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            hallId= resultSet.getString("hallNumber");
            return hallId;
        }
        return null;
    }
}

