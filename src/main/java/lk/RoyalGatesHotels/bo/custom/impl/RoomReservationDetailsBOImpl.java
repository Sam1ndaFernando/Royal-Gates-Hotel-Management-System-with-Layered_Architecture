package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomReservationDetailsBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.RoomReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.RoomReservationDTO;
import lk.RoyalGatesHotels.dto.RoomReservationDetailsDTO;
import lk.RoyalGatesHotels.entity.RoomReservationDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomReservationDetailsBOImpl implements RoomReservationDetailsBO {

    RoomReservationDetailsDAO roomReservationDAO = (RoomReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMRESERVATIONDETAILS);

    @Override
    public List<RoomReservationDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        List<RoomReservationDetails> allEntityData = roomReservationDAO.getAll();
        List<RoomReservationDetailsDTO> data = new ArrayList<>();

        for (RoomReservationDetails reservation : allEntityData) {
            data.add(new RoomReservationDetailsDTO(reservation.getRoom_number(),reservation.getCustomer_id(),reservation.getReservation_id(),reservation.getCheck_out_date(),reservation.getCheck_in_date()));
        }
        return data;
    }

    @Override
    public boolean removeR(String roomReservationId) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.remove(roomReservationId);
    }

    @Override
    public String getRoom(String value) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.getRoom(value);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return roomReservationDAO.add(new RoomReservationDetails(entity.getRoom_number(),entity.getCustomer_id(),entity.getReservation_id(),entity.getCheck_out_date(),entity.getCheck_in_date()));
    }

    @Override
    public boolean update(RoomReservationDetailsDTO entity) throws SQLException, ClassNotFoundException {
        return false;
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
    public RoomReservationDetailsDTO setFields(String id) throws SQLException, ClassNotFoundException {
        RoomReservationDetails reservation = roomReservationDAO.setFields(id);
        return new RoomReservationDetailsDTO(reservation.getRoom_number(),reservation.getCustomer_id(),reservation.getReservation_id(),reservation.getCheck_out_date(),reservation.getCheck_in_date());
    }
}
