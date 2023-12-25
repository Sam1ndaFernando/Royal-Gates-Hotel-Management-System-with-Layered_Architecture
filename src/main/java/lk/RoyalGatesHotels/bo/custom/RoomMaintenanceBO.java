package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.SQLException;
import java.util.List;

public interface RoomMaintenanceBO extends SuperBO {
    public  boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(Maintenance dto) throws SQLException, ClassNotFoundException;

    public boolean update(Maintenance dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public Maintenance setFields(String id) throws SQLException, ClassNotFoundException ;

}
