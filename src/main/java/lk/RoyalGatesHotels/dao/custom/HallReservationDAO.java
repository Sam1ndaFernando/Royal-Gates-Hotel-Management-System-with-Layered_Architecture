package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.HallReservation;

import java.sql.SQLException;

public interface HallReservationDAO extends CrudDAO <HallReservation> {
    public  boolean isValid(String hallNumber) throws SQLException, ClassNotFoundException;

    public HallReservation setHFields(String hallnumber) throws SQLException, ClassNotFoundException;

    // public  boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException ;
}
