package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;


import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsDAO extends CrudDAO<HallReservationDetailTM> {

   public  List<HallReservationDetailTM> getAll() throws SQLException ;
    public  boolean removeH(String hallReservationId) throws SQLException ;
    public  String getHall(String value) throws SQLException ;
}
