package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dto.tm.RoomReservationDetailTM;
import java.sql.SQLException;
import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.entity.RoomReservationDetails;

import java.util.List;


public interface RoomReservationDetailsDAO extends CrudDAO <RoomReservationDetails> {

    public List<RoomReservationDetails> getAll() throws SQLException, ClassNotFoundException;

    public  boolean removeR(String roomReservationId) throws SQLException, ClassNotFoundException;

    public  String getRoom(String value) throws SQLException, ClassNotFoundException;

}
