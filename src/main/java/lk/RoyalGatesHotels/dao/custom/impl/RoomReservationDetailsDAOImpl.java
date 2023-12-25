package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.RoomReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.tm.RoomReservationDetailTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDetailsDAOImpl implements RoomReservationDetailsDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(HallMaintenance entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO roomreservationdetail(reservation_id, room_number) VALUES(?, ?)";
        return SQLUtill.execute(sql, entity.getReservationId(),entity.getRoomNumber());
    }

    @Override
    public boolean update(RoomReservationDetailTM entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE roomreservationdetail SET room_number = ? WHERE reservation_id = ?";
        return SQLUtill.execute(sql, entity.getRoomNumber(), entity.getReservationId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roomreservationdetail WHERE reservation_id = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservationDetailTM setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetail WHERE reservation_id=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String reservationId = resultSet.getString("reservation_id");
            String roomNumber = resultSet.getString("room_number");
            RoomReservationDetailTM roomReservationDetailTM = new RoomReservationDetailTM(reservationId, roomNumber);

            return roomReservationDetailTM;
        }
        return null;
    }

    @Override
    public List<RoomReservationDetailTM> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roomreservationdetail";
        List<RoomReservationDetailTM> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            String reservationId = resultSet.getString("reservation_id");
            String roomNumber = resultSet.getString("room_number");
            RoomReservationDetailTM reservationDetail = new RoomReservationDetailTM(reservationId, roomNumber);
            data.add(reservationDetail);
        }
        return data;
    }

    @Override
    public boolean remove(String roomReservationId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roomreservationdetail WHERE reservation_id = ?";
        return SQLUtill.execute(sql, roomReservationId);
    }

    @Override
    public String getRoom(String value) throws SQLException, ClassNotFoundException {
        String roomId;
        String sql = "SELECT * FROM roomreservationdetail WHERE reservation_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            roomId= resultSet.getString("RoomNumber");
            return roomId;
        }
        return null;
    }
}
