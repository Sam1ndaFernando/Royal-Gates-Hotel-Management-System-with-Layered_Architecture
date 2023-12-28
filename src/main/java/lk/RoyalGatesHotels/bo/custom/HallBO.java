package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.EmployeeDTO;
import lk.RoyalGatesHotels.dto.HallDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(HallDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(HallDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallDTO setFields(String id) throws SQLException, ClassNotFoundException ;
    List<HallDTO> getAllHalls() throws SQLException, ClassNotFoundException;

}
