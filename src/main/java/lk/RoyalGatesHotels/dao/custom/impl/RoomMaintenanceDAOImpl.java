package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.RoomMaintenanceDAO;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceDAOImpl implements RoomMaintenanceDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT maintenanceId FROM roommaintenance ORDER BY maintenanceId DESC LIMIT 1";
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
            String newId = String.format("RM%04d", newNum);
            return newId;
        }
        return "RM0001";
    }

    @Override
    public boolean add(HallMaintenance entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO roommaintenance(maintenanceId, room_number, date, start_time, end_time)Values(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getMaintenanceId(), entity.getNumber(), entity.getDate(), entity.getStartTime(), entity.getEndTime());
    }

    @Override
    public boolean update(Maintenance entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE roommaintenance SET room_number=?, date=?, start_time=?, end_time=? WHERE maintenanceId=?";
        return SQLUtill.execute(sql, entity.getNumber(), entity.getDate(), entity.getStartTime(), entity.getEndTime(), entity.getMaintenanceId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM roommaintenance WHERE maintenanceId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Maintenance setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM roommaintenance WHERE RoomMaintenanceId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String maintenanceId = resultSet.getString("maintenanceId");
            String roomNumber = resultSet.getString("room_number");
            String date = resultSet.getString("date");
            String startTime = resultSet.getString("start_time");
            String endTime = resultSet.getString("end_time");
            return new Maintenance(maintenanceId, roomNumber, date, startTime, endTime);
        }
        return null;
    }

}
