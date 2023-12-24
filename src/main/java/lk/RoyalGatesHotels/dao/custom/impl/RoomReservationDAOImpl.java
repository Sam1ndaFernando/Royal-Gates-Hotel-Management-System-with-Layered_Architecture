package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.custom.RoomReservationDAO;
import lk.RoyalGatesHotels.dto.RoomReservation;

import java.sql.SQLException;
import java.util.List;

public class RoomReservationDAOImpl implements RoomReservationDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RoomReservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RoomReservation entity) throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean isValid(String roomNumber) throws SQLException {
        return false;
    }

    @Override
    public RoomReservation setRFields(String roomnumber) throws SQLException {
        return null;
    }
}
