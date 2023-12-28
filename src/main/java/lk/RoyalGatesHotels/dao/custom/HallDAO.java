package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.HallDTO;
import lk.RoyalGatesHotels.entity.Hall;

import java.sql.SQLException;
import java.util.List;

public interface HallDAO extends CrudDAO <Hall> {
    public List<Hall> getAll() throws SQLException, ClassNotFoundException;

}
