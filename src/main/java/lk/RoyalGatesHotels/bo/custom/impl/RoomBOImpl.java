package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.RoomDAO;
import lk.RoyalGatesHotels.dto.RoomDTO;
import lk.RoyalGatesHotels.entity.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return roomDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("R%04d", newNum);
            return newId;
        }
        return "R0001";
    }

    @Override
    public boolean add(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.add(new Room(dto.getRoom_number(),dto.getRoomType(),dto.getStatus(),dto.getPrice()));
    }

    @Override
    public boolean update(RoomDTO dto) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new Room(dto.getRoom_number(),dto.getRoomType(),dto.getStatus(),dto.getPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return roomDAO.getIds();
    }

    @Override
    public RoomDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Room room = roomDAO.setFields(id);
        return new RoomDTO(room.getRoom_number(),room.getRoomType(),room.getStatus(),room.getPrice());
    }
}
