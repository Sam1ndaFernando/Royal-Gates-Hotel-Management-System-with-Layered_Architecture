package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.HallBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.HallDAO;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.entity.Hall;

import java.sql.SQLException;
import java.util.List;

public class HallBOImpl implements HallBO {
    HallDAO hallDAO = (HallDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALL);
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return hallDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("H%04d", newNum);
            return newId;
        }
        return "H0001";
    }

    @Override
    public boolean add(HallDTO dto) throws SQLException, ClassNotFoundException {
        return hallDAO.add(new Hall(dto.getHallNumber(),dto.getHallType(),dto.getStatus(),dto.getPrice()));
    }

    @Override
    public boolean update(HallDTO dto) throws SQLException, ClassNotFoundException {
        return hallDAO.update(new Hall(dto.getHallNumber(),dto.getHallType(),dto.getStatus(),dto.getPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return hallDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return hallDAO.getIds();
    }

    @Override
    public HallDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Hall hall = hallDAO.setFields(id);
        return new HallDTO(hall.getHallNumber(),hall.getHallType(),hall.getStatus(),hall.getPrice());
    }
}
