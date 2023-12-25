package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dto.tm.RoomReservationDetailTM;
import java.sql.SQLException;
import lk.RoyalGatesHotels.dao.CrudDAO;
import java.util.List;


public interface RoomReservationDetailsDAO extends CrudDAO <RoomReservationDetailTM> {

    public List<RoomReservationDetailTM> getAll() throws SQLException, ClassNotFoundException;

    public  boolean remove(String roomReservationId) throws SQLException, ClassNotFoundException;

    public  String getRoom(String value) throws SQLException, ClassNotFoundException;

}
