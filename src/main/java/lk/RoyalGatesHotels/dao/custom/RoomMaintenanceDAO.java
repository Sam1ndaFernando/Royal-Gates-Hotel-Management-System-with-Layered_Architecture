package lk.RoyalGatesHotels.dao.custom;


import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.SQLException;

public interface RoomMaintenanceDAO extends CrudDAO</*Roommaintenance*/Maintenance> {

    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException ;

}
