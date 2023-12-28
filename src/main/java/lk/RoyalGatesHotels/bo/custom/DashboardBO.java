package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {
    public int getTotalRooms() throws SQLException, ClassNotFoundException;
    public int getTotalHalls() throws SQLException, ClassNotFoundException;
    public int getBookedHalls() throws SQLException, ClassNotFoundException;
    public int getBookedRooms() throws SQLException, ClassNotFoundException;
    public int getComplaints() throws SQLException, ClassNotFoundException;
    public int getAvailableRooms() throws SQLException, ClassNotFoundException;
    public int getAvailableHalls() throws SQLException, ClassNotFoundException;
}
