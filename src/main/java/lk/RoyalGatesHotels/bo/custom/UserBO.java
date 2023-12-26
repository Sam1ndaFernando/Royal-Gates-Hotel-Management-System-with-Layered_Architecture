package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.UsersDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public  boolean empCheck(String empId) throws SQLException;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(UsersDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(UsersDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public UsersDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
