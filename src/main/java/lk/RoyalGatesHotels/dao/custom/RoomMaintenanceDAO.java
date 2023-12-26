package lk.RoyalGatesHotels.dao.custom;


import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.MaintenanceDTO;
import lk.RoyalGatesHotels.entity.RoomMaintenance;

import java.sql.SQLException;

public interface RoomMaintenanceDAO extends CrudDAO<RoomMaintenance> {
    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException, ClassNotFoundException;
}
