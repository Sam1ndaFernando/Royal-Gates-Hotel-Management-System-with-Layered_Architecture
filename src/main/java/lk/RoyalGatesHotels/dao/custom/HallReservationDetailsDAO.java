package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;
import lk.RoyalGatesHotels.entity.HallReservation;
import lk.RoyalGatesHotels.entity.HallReservationDetails;


import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsDAO extends CrudDAO<HallReservationDetails> {

   public  List<HallReservationDetails> getAll() throws SQLException, ClassNotFoundException;
    public  boolean removeH(String hallReservationId) throws SQLException, ClassNotFoundException;
    public  String getHall(String value) throws SQLException, ClassNotFoundException;
}
