package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomReservationBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.RoomReservationDAO;
import lk.RoyalGatesHotels.dao.custom.RoomReservationDetailsDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.entity.RoomReservation;
import lk.RoyalGatesHotels.entity.RoomReservationDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomReservationBOImpl implements RoomReservationBO {
    RoomReservationDAO roomReservationDAO = (RoomReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATION);
    RoomReservationDetailsDAO roomReservationDetailsDAO = (RoomReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATIONDETAILS);


    @Override
    public boolean isValid(String roomNumber) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.isValid(roomNumber);
    }

    @Override
    public RoomReservationDTO setRFields(String roomnumber) throws SQLException, ClassNotFoundException {
        RoomReservation roomreservation = roomReservationDAO.setRFields(roomnumber);
        return new RoomReservationDTO(roomreservation.getRoom_number(),roomreservation.getCustomer_id(),roomreservation.getReservation_id(),roomreservation.getCheck_out_date(),roomreservation.getCheck_in_date());
    }

    @Override
    public boolean Order(String checkIn, String checkOut, String roomReservationId, String guestId, String roomNumber) throws SQLException {
        Connection con = null;
        try{
            con= DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = roomReservationDAO.add(new RoomReservation(roomNumber, guestId, roomReservationId, checkOut, checkIn));
            if(isSaved){
                boolean isAdded=  roomReservationDetailsDAO.add(new RoomReservationDetails(roomNumber, guestId, roomReservationId, checkOut, checkIn));
                if (isAdded){
                    con.commit();
                    return true;
                }
            }
            return false;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return roomReservationDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RR%04d", newNum);
            return newId;
        }
        return "RR0001";
    }

    @Override
    public boolean add(RoomReservationDTO dto) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.add(new RoomReservation(dto.getRoom_number(),dto.getCustomer_id(),dto.getReservation_id(),dto.getCheck_out_date(),dto.getCheck_in_date()));

    }

    @Override
    public boolean update(RoomReservationDTO dto) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.update(new RoomReservation(dto.getRoom_number(),dto.getCustomer_id(),dto.getReservation_id(),dto.getCheck_out_date(),dto.getCheck_in_date()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public RoomReservationDTO setFields(String id) throws SQLException, ClassNotFoundException {
        RoomReservation roomreservation = roomReservationDAO.setFields(id);
        return new RoomReservationDTO(roomreservation.getRoom_number(),roomreservation.getCustomer_id(),roomreservation.getReservation_id(),roomreservation.getCheck_out_date(),roomreservation.getCheck_in_date());
    }
}
