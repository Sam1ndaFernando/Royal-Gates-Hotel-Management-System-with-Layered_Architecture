package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.UserBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.UserDAO;
import lk.RoyalGatesHotels.dto.UsersDTO;
import lk.RoyalGatesHotels.entity.Users;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO user = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean empCheck(String empId) throws SQLException, ClassNotFoundException {
        return user.empCheck(empId);
    }

    @Override
    public boolean elegibleCheck(String userName, String password) throws SQLException, ClassNotFoundException {
        return user.elegibleCheck(userName, password);
    }

    @Override
    public boolean getValid(String userName) throws SQLException, ClassNotFoundException {
        return user.getValid(userName);
    }

    @Override
    public boolean RecoverUpdate(String userName, String password) throws SQLException, ClassNotFoundException {
        return user.RecoverUpdate(userName, password);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return user.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("U%04d", newNum);
            return newId;
        }
        return "U0001";
    }

    @Override
    public boolean add(UsersDTO dto) throws SQLException, ClassNotFoundException {
        return user.add(new Users(dto.getEmpId(), dto.getName(), dto.getJobRole(), dto.getUsername(), dto.getPassword()));
    }

    @Override
    public boolean update(UsersDTO dto) throws SQLException, ClassNotFoundException {
        return user.update(new Users(dto.getEmpId(), dto.getName(), dto.getJobRole(), dto.getUsername(), dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return user.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return user.getIds();
    }

    @Override
    public UsersDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Users users = user.setFields(id);
        return new UsersDTO(users.getEmpId(), users.getName(), users.getJobRole(), users.getUsername(), users.getPassword());
    }
}
