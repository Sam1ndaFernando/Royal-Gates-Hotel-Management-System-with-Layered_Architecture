package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationBO extends SuperBO {
    public  boolean isValid(String roomNumber) throws SQLException, ClassNotFoundException;

    public RoomReservationDTO setRFields(String roomnumber) throws SQLException, ClassNotFoundException;

    public  boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(RoomReservationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(RoomReservationDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomReservationDTO setFields(String id) throws SQLException, ClassNotFoundException;
}
