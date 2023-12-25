package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(Employee dto) throws SQLException, ClassNotFoundException;

    public boolean update(Employee dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public Employee setFields(String id) throws SQLException, ClassNotFoundException ;
}
