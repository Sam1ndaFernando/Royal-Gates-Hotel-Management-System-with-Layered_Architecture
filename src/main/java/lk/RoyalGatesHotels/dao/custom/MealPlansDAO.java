package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.MealPackges;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansDAO extends CrudDAO <MealPackges> {

    public List<MealPackges> getAll() throws SQLException, ClassNotFoundException;
    public  String getItems(String packageId) throws SQLException, ClassNotFoundException;

}
