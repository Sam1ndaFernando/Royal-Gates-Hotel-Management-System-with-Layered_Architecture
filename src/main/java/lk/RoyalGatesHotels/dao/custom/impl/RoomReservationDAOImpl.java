package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.RoomReservationDAO;
import lk.RoyalGatesHotels.dto.HallMaintenanceDTO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.entity.RoomReservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomReservationDAOImpl implements RoomReservationDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT RoomReservationId FROM RoomReservation ORDER BY RoomReservationId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RR%04d", newNum);
            return newId;
        }
        return "RR0001";
    }

    @Override
    public boolean add(RoomReservation entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO roomreservationdetail(room_number, customer_id, reservation_id, check_out_date, check_in_date) VALUES(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getRoom_number(), entity.getCustomer_id(), entity.getReservation_id(), entity.getCheck_out_date(), entity.getCheck_in_date());
    }

    @Override
    public boolean update(RoomReservation entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE roomreservationdetail SET room_number=?, customer_id=?, check_out_date=?, check_in_date=? WHERE reservation_id=?";
        return SQLUtill.execute(sql, entity.getRoom_number(), entity.getCustomer_id(), entity.getCheck_out_date(), entity.getCheck_in_date(), entity.getReservation_id());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roomreservationdetail WHERE reservation_id=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservation setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetail WHERE reservation_id=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String roomNumber = resultSet.getString("room_number");
            String customerId = resultSet.getString("customer_id");
            String reservationId = resultSet.getString("reservation_id");
            String checkInDate = String.valueOf(resultSet.getDate("check_in_date"));
            String checkOutDate = String.valueOf(resultSet.getDate("check_out_date"));

            return new RoomReservation(roomNumber, customerId, reservationId, checkInDate, checkOutDate);
        }
        return null;
    }

    @Override
    public boolean isValid(String roomNumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetail WHERE room_number=?";
        ResultSet resultSet = SQLUtill.execute(sql, roomNumber);
        if(resultSet.next()){
            return  true;
        }
        return false;
    }

    @Override
    public RoomReservation setRFields(String roomnumber) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetail WHERE room_number=?";
        ResultSet resultSet = SQLUtill.execute(sql, roomnumber);
        if (resultSet.next()) {
            String customerId = resultSet.getString("customer_id");
            String reservationId = resultSet.getString("reservation_id");
            String checkInDate = String.valueOf(resultSet.getDate("check_in_date"));
            String checkOutDate = String.valueOf(resultSet.getDate("check_out_date"));
            return new RoomReservation(roomnumber, customerId, reservationId, checkInDate, checkOutDate);
        }

        return null;
    }
}
