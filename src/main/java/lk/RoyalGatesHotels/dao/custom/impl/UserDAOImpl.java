package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.UserDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Employee_id FROM user ORDER BY Employee_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("U%04d", newNum);
            return newId;
        }
        return "U0001";
    }

    @Override
    public boolean add(HallMaintenance entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO user(Employee_id, name, job_role, username, password) VALUES(?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getEmpId(), entity.getName(), entity.getJobRole(), entity.getUsername(), entity.getPassword());
    }

    @Override
    public boolean update(Users entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET name = ?, job_role = ?, username = ?, password = ? WHERE Employee_id = ?";
        return SQLUtill.execute(sql, entity.getName(), entity.getJobRole(), entity.getUsername(), entity.getPassword(), entity.getEmpId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM user WHERE Employee_id = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT Employee_id FROM user";
        List<String> employeeIds = new ArrayList<>();
        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while (resultSet.next()) {
            employeeIds.add(resultSet.getString(1));
        }
        return employeeIds;
    }

    @Override
    public Users setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE Employee_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String employeeId = resultSet.getString("Employee_id");
            String name = resultSet.getString("name");
            String jobRole = resultSet.getString("job_role");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            return new Users(employeeId, name, jobRole, username, password);
        }
        return null;
    }

    @Override
    public boolean empCheck(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE Employee_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, empId);
        return resultSet.next();
    }

    @Override
    public boolean elegibleCheck(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        ResultSet resultSet = SQLUtill.execute(sql, userName, password);
        return resultSet.next();
    }

    @Override
    public boolean getValid(String userName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user WHERE username = ?";
        ResultSet resultSet = SQLUtill.execute(sql, userName);
        return resultSet.next();
    }

    @Override
    public boolean RecoverUpdate(String userName, String password) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        return SQLUtill.execute(sql, password, userName);
    }
}
