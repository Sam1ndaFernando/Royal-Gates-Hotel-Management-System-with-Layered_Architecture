package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.RoomReservation;

import java.sql.SQLException;

public interface RoomReservationDAO extends CrudDAO<RoomReservation> {
    public  boolean isValid(String roomNumber) throws SQLException, ClassNotFoundException;

    public RoomReservation setRFields(String roomnumber) throws SQLException, ClassNotFoundException;

 //   public  boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException ;

}
