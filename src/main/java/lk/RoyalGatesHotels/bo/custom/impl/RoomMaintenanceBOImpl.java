package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomMaintenanceBO;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceBOImpl implements RoomMaintenanceBO {
    @Override
    public boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
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
    public boolean add(Maintenance dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Maintenance dto) throws SQLException, ClassNotFoundException {
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
}
