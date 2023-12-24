package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.custom.RoomReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.tm.RoomReservationDetailTM;

import java.sql.SQLException;
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
    public boolean add(RoomReservationDetailTM entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RoomReservationDetailTM entity) throws SQLException, ClassNotFoundException {
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
    public RoomReservationDetailTM setFields(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<RoomReservationDetailTM> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean remove(String roomReservationId) throws SQLException {
        return false;
    }

    @Override
    public String getRoom(String value) throws SQLException {
        return null;
    }
}
