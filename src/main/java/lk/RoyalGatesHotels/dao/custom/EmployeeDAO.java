package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.EmployeeDTO;
import lk.RoyalGatesHotels.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO extends CrudDAO <Employee> {
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException;
    List<Employee> getAll() throws SQLException, ClassNotFoundException;
}
