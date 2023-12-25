package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.UserBO;
import lk.RoyalGatesHotels.dto.Users;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
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

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Users dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Users dto) throws SQLException, ClassNotFoundException {
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
}
