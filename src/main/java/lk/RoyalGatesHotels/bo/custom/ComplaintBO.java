package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.Complain;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintBO extends SuperBO {
    public String getRIds() throws SQLException, ClassNotFoundException;
    public  List<String> getGIds() throws SQLException, ClassNotFoundException;
    public  List<String> getHIds() throws SQLException, ClassNotFoundException;
    public  List<String> getMIds() throws SQLException, ClassNotFoundException;

    public  List<Complain> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(Complain dto) throws SQLException, ClassNotFoundException;

    public boolean update(Complain dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public Complain setFields(String id) throws SQLException, ClassNotFoundException;

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;
}
