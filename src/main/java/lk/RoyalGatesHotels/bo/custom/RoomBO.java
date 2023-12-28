package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.dto.RoomDTO;

import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(RoomDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(RoomDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public RoomDTO setFields(String id) throws SQLException, ClassNotFoundException;

    public List<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;
}
