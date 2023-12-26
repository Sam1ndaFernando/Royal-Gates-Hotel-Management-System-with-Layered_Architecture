package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dto.UsersDTO;
import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.entity.Users;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<Users> {

    public  boolean empCheck(String empId) throws SQLException, ClassNotFoundException;

    public  boolean elegibleCheck(String userName, String password) throws SQLException, ClassNotFoundException;

    public  boolean getValid(String userName) throws SQLException, ClassNotFoundException;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException, ClassNotFoundException;
}
