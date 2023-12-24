package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.custom.RoomMaintenanceDAO;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceDAOImpl implements RoomMaintenanceDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Maintenance entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Maintenance entity) throws SQLException, ClassNotFoundException {
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
    public Maintenance setFields(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
        return false;
    }
}
