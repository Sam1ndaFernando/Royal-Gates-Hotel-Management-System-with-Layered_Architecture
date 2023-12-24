package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.custom.UserDAO;
import lk.RoyalGatesHotels.dto.Users;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Users entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Users entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Users setFields(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean empCheck(String empId) throws SQLException {
        return false;
    }

    @Override
    public boolean elegibleCheck(String userName, String password) throws SQLException {
        return false;
    }

    @Override
    public boolean getValid(String userName) throws SQLException {
        return false;
    }

    @Override
    public boolean RecoverUpdate(String userName, String password) throws SQLException {
        return false;
    }
}
