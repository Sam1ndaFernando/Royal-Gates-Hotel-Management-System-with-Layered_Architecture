package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.dto.RoomReservationDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationDetailsBO extends SuperBO {
    public  List<RoomReservationDetailsDTO> getAll() throws SQLException, ClassNotFoundException;

    public  boolean removeR(String roomReservationId) throws SQLException, ClassNotFoundException;

    public  String getRoom(String value) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomReservationDetailsDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
