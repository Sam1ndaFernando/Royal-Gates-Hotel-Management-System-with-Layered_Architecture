package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomReservationDetailsBO;
import lk.RoyalGatesHotels.dto.RoomReservation;

import java.sql.SQLException;
import java.util.List;

public class RoomReservationDetailsBOImpl implements RoomReservationDetailsBO {
    @Override
    public List<RoomReservation> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean removeR(String roomReservationId) throws SQLException {
        return false;
    }

    @Override
    public String getRoom(String value) throws SQLException {
        return null;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RoomReservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RoomReservation dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservation setFields(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
