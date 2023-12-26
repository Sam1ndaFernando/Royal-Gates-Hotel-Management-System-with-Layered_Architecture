package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(EmployeeDTO entity) throws SQLException, ClassNotFoundException;

    public boolean update(EmployeeDTO entity) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public EmployeeDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
