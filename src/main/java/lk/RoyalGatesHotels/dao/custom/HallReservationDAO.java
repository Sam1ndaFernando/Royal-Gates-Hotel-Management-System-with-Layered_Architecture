package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.entity.HallReservation;

import java.sql.SQLException;

public interface HallReservationDAO extends CrudDAO <HallReservation> {
    public  boolean isValid(String hallNumber) throws SQLException, ClassNotFoundException;

    public HallReservation setHFields(String hallnumber) throws SQLException, ClassNotFoundException;

}
