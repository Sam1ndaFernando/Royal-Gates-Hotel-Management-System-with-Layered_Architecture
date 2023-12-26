package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.HallReservationDetailsBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.HallReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.dto.HallReservationDetailsDTO;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;
import lk.RoyalGatesHotels.entity.HallReservationDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallReservationDetailsBOImpl implements HallReservationDetailsBO {

    HallReservationDetailsDAO dao = (HallReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATIONDETAILS);
    @Override
    public List<HallReservationDetailsDTO> getAll() throws SQLException, ClassNotFoundException {
        List<HallReservationDetails> allEntityData = dao.getAll();
        List<HallReservationDetailsDTO> data = new ArrayList<>();

        for (HallReservationDetails hallReservationDetails : allEntityData) {
            data.add(new HallReservationDetailsDTO(hallReservationDetails.getHall_number(),hallReservationDetails.getCustomer_id(),hallReservationDetails.getReservation_id(),hallReservationDetails.getCheck_out_date(),hallReservationDetails.getCheck_in_date()));
        }
        return data;
    }

    @Override
    public boolean removeH(String hallReservationId) throws SQLException, ClassNotFoundException {
        return dao.removeH(hallReservationId);
    }

    @Override
    public String getHall(String id) throws SQLException, ClassNotFoundException {
        return dao.getHall(id);
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
    public boolean add(HallReservationDTO dto) throws SQLException, ClassNotFoundException {
        return dao.add(new HallReservationDetails(dto.getHall_number(),dto.getCustomer_id(),dto.getReservation_id(),dto.getCheck_out_date(),dto.getCheck_in_date()));
    }

    @Override
    public boolean update(HallReservationDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HallReservationDTO setFields(String id) throws SQLException, ClassNotFoundException {
        HallReservationDetails hallReservationDetails = dao.setFields(id);
        return new HallReservationDTO(hallReservationDetails.getHall_number(),hallReservationDetails.getCustomer_id(),hallReservationDetails.getReservation_id(),hallReservationDetails.getCheck_out_date(),hallReservationDetails.getCheck_in_date());
    }
}
