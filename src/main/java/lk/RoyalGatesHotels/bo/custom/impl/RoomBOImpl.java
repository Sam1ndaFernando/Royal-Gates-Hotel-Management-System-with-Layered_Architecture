package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomBO;
import lk.RoyalGatesHotels.dto.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Room dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Room dto) throws SQLException, ClassNotFoundException {
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
    public Room setFields(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
