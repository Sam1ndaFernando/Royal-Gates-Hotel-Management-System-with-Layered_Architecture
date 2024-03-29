package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.EmployeeBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.EmployeeDAO;
import lk.RoyalGatesHotels.dto.EmployeeDTO;
import lk.RoyalGatesHotels.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public boolean add(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmployeeId(),dto.getName(),dto.getAddress(),dto.getJoin_date(),dto.getNic(),dto.getEmail(),dto.getMobile(),dto.getJobRole()));
    }

    @Override
    public boolean update(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
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
    public EmployeeDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Employee emp = employeeDAO.setFields(id);
        return new EmployeeDTO(emp.getEmployeeId(),emp.getName(),emp.getAddress(),emp.getJoin_date(),emp.getNic(),emp.getEmail(),emp.getMobile(),emp.getJobRole());

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException {
        List<Employee> employeeList = employeeDAO.getAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee e : employeeList) {
            employeeDTOList.add(
                    new EmployeeDTO(
                            e.getEmployeeId(),
                            e.getName(),
                            e.getAddress(),
                            e.getJoin_date(),
                            e.getNic(),
                            e.getEmail(),
                            e.getMobile(),
                            e.getJobRole())
            );
        }
        return employeeDTOList;
    }

}
