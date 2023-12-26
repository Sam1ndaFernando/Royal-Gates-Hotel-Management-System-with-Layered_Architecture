package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.entity.RoomReservation;

import java.sql.SQLException;

public interface RoomReservationDAO extends CrudDAO<RoomReservation> {
    public  boolean isValid(String roomNumber) throws SQLException, ClassNotFoundException;

    public RoomReservation setRFields(String roomnumber) throws SQLException, ClassNotFoundException;

}
