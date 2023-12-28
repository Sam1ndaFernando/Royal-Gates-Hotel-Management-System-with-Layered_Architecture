package lk.RoyalGatesHotels.dao.custom;


import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.entity.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    public List<Room> getAll() throws SQLException, ClassNotFoundException;

}
