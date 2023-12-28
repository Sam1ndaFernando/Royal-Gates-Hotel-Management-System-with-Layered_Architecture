package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.MealPackgesDTO;

import java.sql.SQLException;
import java.util.List;

public interface MealPlansBO extends SuperBO {
    public List<MealPackgesDTO> getAll() throws SQLException, ClassNotFoundException;

    public  String getItems(String packageId) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(MealPackgesDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(MealPackgesDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public MealPackgesDTO setFields(String id) throws SQLException, ClassNotFoundException ;

}
