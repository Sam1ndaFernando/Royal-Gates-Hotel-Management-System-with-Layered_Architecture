package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.ComplainDTO;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintBO extends SuperBO {
    public  List<String> getRIds() throws SQLException, ClassNotFoundException;
    public  List<String> getGIds() throws SQLException, ClassNotFoundException;
    public  List<String> getHIds() throws SQLException, ClassNotFoundException;
    public  List<String> getMIds() throws SQLException, ClassNotFoundException;

    public  List<ComplainDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(ComplainDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(ComplainDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public ComplainDTO setFields(String id) throws SQLException, ClassNotFoundException;

    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;
}
