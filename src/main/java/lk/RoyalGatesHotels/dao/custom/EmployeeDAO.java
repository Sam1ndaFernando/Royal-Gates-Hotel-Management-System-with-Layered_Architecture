package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO <Employee> {
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException;
}
