package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.MealOders;
import lk.RoyalGatesHotels.dto.MealPackges;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansBO extends SuperBO {
    public List<MealPackges> getAll() throws SQLException, ClassNotFoundException;

    public  String getItems(String packageId) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(MealPackges dto) throws SQLException, ClassNotFoundException;

    public boolean update(MealPackges dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public MealPackges setFields(String id) throws SQLException, ClassNotFoundException ;
}
