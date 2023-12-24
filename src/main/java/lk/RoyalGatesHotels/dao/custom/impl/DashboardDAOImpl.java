package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.custom.DashboardDAO;
import lk.RoyalGatesHotels.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public int getTotalRooms() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS roomCount FROM room");
        rs.next();
        int numRooms = rs.getInt("roomCount");
        return numRooms;
    }

    @Override
    public int getTotalHalls() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS hallsCount FROM hall");
        rs.next();
        int numhalls = rs.getInt("hallsCount");
        return numhalls;
    }

    @Override
    public int getBookedHalls() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS bookedHalls FROM hallreservationdetail");
        rs.next();
        int booked_hall = rs.getInt("bookedHalls");
        return booked_hall;
    }

    @Override
    public int getBookedRooms() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS booked_rooms FROM roomreservationdetail");
        rs.next();
        int booked_room = rs.getInt("booked_rooms");
        return booked_room;
    }

    @Override
    public int getComplaints() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS com FROM complain");
        rs.next();
        int complaints = rs.getInt("com");
        return complaints;
    }

    @Override
    public int getAvailableRooms() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet totalRoomsResultSet = stmt.executeQuery("SELECT COUNT(*) AS totalRooms FROM room");
        totalRoomsResultSet.next();
        int totalRooms = totalRoomsResultSet.getInt("totalRooms");
        int bookedRooms = getBookedRooms();
        int availableRooms = totalRooms - bookedRooms;
        return availableRooms;
    }

    @Override
    public int getAvailableHalls() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet totalHallsResultSet = stmt.executeQuery("SELECT COUNT(*) AS totalHalls FROM halls");
        totalHallsResultSet.next();
        int totalHalls = totalHallsResultSet.getInt("totalHalls");
        int bookedHalls = getBookedHalls();
        int availableHalls = totalHalls - bookedHalls;
        return availableHalls;
    }

}
