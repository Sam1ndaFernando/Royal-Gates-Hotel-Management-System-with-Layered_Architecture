package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.UsersDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public  boolean empCheck(String empId) throws SQLException, ClassNotFoundException;

    public  boolean elegibleCheck(String userName, String password) throws SQLException, ClassNotFoundException;

    public  boolean getValid(String userName) throws SQLException, ClassNotFoundException;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(UsersDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(UsersDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public UsersDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
