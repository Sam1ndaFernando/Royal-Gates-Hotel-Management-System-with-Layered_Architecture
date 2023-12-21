package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dto.Users;
import lk.RoyalGatesHotels.dao.CrudDAO;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<Users> {

    public  boolean empCheck(String empId) throws SQLException ;

    public  boolean elegibleCheck(String userName, String password) throws SQLException ;

    public  boolean getValid(String userName) throws SQLException ;

    public  boolean RecoverUpdate(String userName, String password) throws SQLException ;
}
