package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.GuestDTO;

import java.sql.SQLException;
import java.util.List;

public interface GuestBO extends SuperBO {
    public  String getName(String value) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(GuestDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(GuestDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public GuestDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
