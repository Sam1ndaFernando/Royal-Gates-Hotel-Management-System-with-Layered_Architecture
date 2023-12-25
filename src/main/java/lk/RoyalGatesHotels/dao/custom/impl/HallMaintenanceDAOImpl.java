package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.HallMaintenanceDAO;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HallMaintenanceDAOImpl implements HallMaintenanceDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT maintenanceId FROM hallmaintenance ORDER BY maintenanceId DESC LIMIT 1";
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
            String newId = String.format("HM%04d", newNum);
            return newId;
        }
        return "HM0001";
    }

    @Override
    public boolean add(HallMaintenance entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO hallmaintenance(maintenanceId, hall_number, date, start_time, end_time)Values(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getMaintenanceId(), entity.getNumber(), entity.getDate(), entity.getStartTime(), entity.getEndTime());
    }

    @Override
    public boolean update(Maintenance entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE hallmaintenance SET hall_number=?, date=?, start_time=?, end_time=? WHERE maintenanceId=?";
        return SQLUtill.execute(sql, entity.getNumber(), entity.getDate(), entity.getStartTime(), entity.getEndTime(), entity.getMaintenanceId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM hallmaintenance WHERE maintenanceId=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Maintenance setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM hallmaintenance WHERE maintenanceId = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String maintenanceId = resultSet.getString("maintenanceId");
            String hallNumber = resultSet.getString("hall_number");
            String date = String.valueOf(resultSet.getDate("date"));
            String startTime = String.valueOf(resultSet.getTime("start_time"));
            String endTime = String.valueOf(resultSet.getTime("end_time"));

            return new Maintenance(maintenanceId, hallNumber, date, startTime, endTime);
        }
        return null;
    }
}
