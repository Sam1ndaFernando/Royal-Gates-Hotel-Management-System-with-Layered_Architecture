package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.dto.HallReservationDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsBO extends SuperBO {
    public List<HallReservationDetailsDTO> getAll() throws SQLException, ClassNotFoundException;
    public  boolean removeH(String hallReservationId) throws SQLException, ClassNotFoundException;
    public  String getHall(String id) throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
