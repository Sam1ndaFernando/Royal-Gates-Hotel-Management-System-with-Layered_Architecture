package lk.RoyalGatesHotels.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SuperDAO{
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public T setFields(String id) throws SQLException, ClassNotFoundException ;
}
