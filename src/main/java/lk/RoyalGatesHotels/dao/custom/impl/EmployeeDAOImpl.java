package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.EmployeeDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT employeeId FROM employee ORDER BY employeeId DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if (currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("E%04d", newNum);
            return newId;
        }
        return "E0001";
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO employee (employeeId, name, address, join_date, nic, Email, mobile, jobRole) VALUES (?,?,?,?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getEmployeeId(), entity.getName(), entity.getAddress(), entity.getJoin_date(), entity.getNic(), entity.getEmail(), entity.getMobile(), entity.getJobRole());
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
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
    public Employee setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM employee WHERE employeeId=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String employeeId = resultSet.getString("employeeId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String joinDate = String.valueOf(resultSet.getDate("join_date"));
            String nic = resultSet.getString("nic");
            String email = resultSet.getString("Email");
            String mobile = resultSet.getString("mobile");
            String jobRole = resultSet.getString("jobRole");

            return new Employee(employeeId, name, address, joinDate, nic, email, mobile, jobRole);
        }
        return null;
    }

    @Override
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT (*) AS count FROM employee WHERE employeeId=? AND jobRole='Admin'";
        try (ResultSet resultSet = SQLUtill.execute(sql, empId)) {
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    @Override
    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM employee");
        List<Employee> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Employee(
                    rst.getString("employeeId"),
                    rst.getString("nic"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("mobile"),
                    String.valueOf(rst.getDate("join_date")),
                    rst.getString("jobRole"),
                    rst.getString("Email"))
            );
        }
        return list;
    }


}
