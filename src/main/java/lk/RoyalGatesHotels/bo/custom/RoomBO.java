package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(Room dto) throws SQLException, ClassNotFoundException;

    public boolean update(Room dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public Room setFields(String id) throws SQLException, ClassNotFoundException ;

}
