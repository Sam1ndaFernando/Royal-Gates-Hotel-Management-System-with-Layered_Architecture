package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationBO extends SuperBO {
    public  boolean isValid(String hallNumber) throws SQLException, ClassNotFoundException;

    public HallReservationDTO setHFields(String hallnumber) throws SQLException, ClassNotFoundException;

    public  boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
