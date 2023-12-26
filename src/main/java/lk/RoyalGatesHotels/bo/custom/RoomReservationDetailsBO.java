package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomReservationDetailsBO extends SuperBO {
    public List<RoomReservationDTO> getAll() throws SQLException;

    public  boolean removeR(String roomReservationId) throws SQLException ;

    public  String getRoom(String value) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(RoomReservationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(RoomReservationDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
