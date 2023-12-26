package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.EmployeeDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT EmpId FROM employee ORDER BY EmpId DESC LIMIT 1";
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
            String newId = String.format("E%04d", newNum);
            return newId;
        }
        return "E0001";
    }

    @Override
    public boolean add(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee (employeeId, name, address, join_date, nic, Email, mobile, jobRole) VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getEmployeeId(), entity.getName(), entity.getAddress(), entity.getJoin_date(), entity.getNic(), entity.getEmail(), entity.getMobile(), entity.getJobRole());
    }

    @Override
    public boolean update(EmployeeDTO entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE employee SET name=?, address=?, join_date=?, nic =?, Email=?, mobile=?, jobRole=? WHERE employeeId=?";
        return SQLUtill.execute(sql, entity.getName(), entity.getAddress(), entity.getJoin_date(), entity.getNic(), entity.getEmail(), entity.getMobile(), entity.getJobRole(), entity.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM employee WHERE employeeId=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> employeeIds = new ArrayList<>();
        Connection con = DBConnection.getInstance().getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT employeeId FROM employee");
        while (rs.next()) {
            employeeIds.add(rs.getString("employeeId"));
        }
        return employeeIds;
    }

    @Override
    public EmployeeDTO setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE employeeId=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String employeeId = resultSet.getString("employeeId");
            String nic = resultSet.getString("nic");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String mobile = resultSet.getString("mobile");
            String joinDate = String.valueOf(resultSet.getDate("join_date"));
            String jobRole = resultSet.getString("jobRole");
            String email = resultSet.getString("Email");
            return new EmployeeDTO(employeeId, nic, name, address, mobile, joinDate, jobRole, email);
        }
        return null;
    }

    @Override
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT (*) AS count FROM employee WHERE employeeId=? AND jobRole='Admin'";
        try (ResultSet resultSet = SQLUtill.execute(sql, empId)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count>0;
            }
        }
        return false;
    }
}
