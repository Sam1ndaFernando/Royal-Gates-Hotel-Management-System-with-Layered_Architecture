package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;

import java.sql.SQLException;
import java.util.List;

public interface HallReservationDetailsBO extends SuperBO {
    public List<HallReservationDetailTM> getAll() throws SQLException;
    public  boolean removeH(String hallReservationId) throws SQLException ;
    public  String getHall(String id) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String currentId) throws SQLException, ClassNotFoundException;

    public boolean add(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(HallReservationDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException ;
}
