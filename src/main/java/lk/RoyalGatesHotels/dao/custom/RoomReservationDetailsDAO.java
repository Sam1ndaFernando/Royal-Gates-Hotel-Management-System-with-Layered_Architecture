package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dto.tm.RoomReservationDetailTM;
import java.sql.SQLException;
import lk.RoyalGatesHotels.dao.CrudDAO;
import java.util.List;


public interface RoomReservationDetailsDAO extends CrudDAO <RoomReservationDetailTM> {

    public List<RoomReservationDetailTM> getAll() throws SQLException ;

    public  boolean removeR(String roomReservationId) throws SQLException ;

    public  String getRoom(String value) throws SQLException ;

}
