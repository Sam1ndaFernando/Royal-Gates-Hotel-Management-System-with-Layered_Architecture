package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.MaintenanceDTO;
import lk.RoyalGatesHotels.dto.RoomMaintenanceDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomMaintenanceBO extends SuperBO {
    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(RoomMaintenanceDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(RoomMaintenanceDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomMaintenanceDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
