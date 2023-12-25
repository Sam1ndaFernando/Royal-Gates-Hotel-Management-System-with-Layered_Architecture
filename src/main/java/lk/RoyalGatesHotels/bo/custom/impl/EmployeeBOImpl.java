package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.EmployeeBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.EmployeeDAO;
import lk.RoyalGatesHotels.dto.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public boolean roleCheck(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.roleCheck(empId);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("E%04d", newNum);
            return newId;
        }
        return "E0001";
    }

    @Override
    public boolean add(Employee dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),dto.getJoin_date(),dto.getNic(),dto.getEmail(),dto.getMobile(),dto.getJobRole()));
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),dto.getJoin_date(),dto.getNic(),dto.getEmail(),dto.getMobile(),dto.getJobRole()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return employeeDAO.getIds();
    }

    @Override
    public Employee setFields(String id) throws SQLException, ClassNotFoundException {
        Employee emp = employeeDAO.setFields(id);
        return new Employee(emp.getEmployeeId(),emp.getName(),emp.getAddress(),emp.getJoin_date(),emp.getNic(),emp.getEmail(),emp.getMobile(),emp.getJobRole());
    }
}
