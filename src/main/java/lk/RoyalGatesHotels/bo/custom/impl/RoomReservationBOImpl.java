package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomReservationBO;
import lk.RoyalGatesHotels.dto.RoomReservation;

import java.sql.SQLException;
import java.util.List;

public class RoomReservationBOImpl implements RoomReservationBO {
    @Override
    public boolean isValid(String roomNumber) throws SQLException {
        return false;
    }

    @Override
    public RoomReservation setRFields(String roomnumber) throws SQLException {
        return null;
    }

    @Override
    public boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
        return false;
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
