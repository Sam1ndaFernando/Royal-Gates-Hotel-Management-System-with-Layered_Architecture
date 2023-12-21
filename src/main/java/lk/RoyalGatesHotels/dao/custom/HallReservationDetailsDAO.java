package lk.RoyalGatesHotels.dao.custom;

import lk.ijse.reservate.dao.CrudDAO;
import lk.ijse.reservate.entity.HallReservationDetails;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsDAO extends CrudDAO<HallReservationDetails> {

   public  List<HallReservationDetails> getAll() throws SQLException ;
    public  boolean removeH(String hallReservationId) throws SQLException ;
    public  String getHall(String value) throws SQLException ;
}
