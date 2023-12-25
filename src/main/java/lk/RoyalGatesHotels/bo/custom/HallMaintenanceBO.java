package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.HallMaintenance;

import java.sql.SQLException;
import java.util.List;

public interface HallMaintenanceBO extends SuperBO {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(HallMaintenance dto) throws SQLException, ClassNotFoundException;

    public boolean update(HallMaintenance dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallMaintenance setFields(String id) throws SQLException, ClassNotFoundException ;
}
