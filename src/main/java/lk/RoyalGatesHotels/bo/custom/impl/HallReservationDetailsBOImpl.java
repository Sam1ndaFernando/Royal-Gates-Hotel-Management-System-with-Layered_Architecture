package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.HallReservationDetailsBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.HallReservationDetailsDAO;
import lk.RoyalGatesHotels.dto.HallReservationDTO;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallReservationDetailsBOImpl implements HallReservationDetailsBO {

    HallReservationDetailsDAO dao = (HallReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATIONDETAILS);
    @Override
    public List<HallReservationDetailTM> getAll() throws SQLException {
        List<HallReservationDetailTM> allEntityData = dao.getAll();
        List<HallReservationDetailTM> data = new ArrayList<>();
        for (HallReservationDetailTM hallReservationDetails : allEntityData) {
            data.add(new HallReservationDetailTM(hallReservationDetails.getReservationId(), hallReservationDetails.getHallNUmber()));
        }
        return data;
    }

    @Override
    public boolean removeH(String hallReservationId) throws SQLException {
        return dao.removeH(hallReservationId);
    }

    @Override
    public String getHall(String id) throws SQLException {
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
        return dao.add(new HallReservationDetailTM(dto.getHall_number(),dto.getReservation_id()));
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
        HallReservationDetailTM hallReservationDetails = dao.setFields(id);
        return new HallReservationDTO(hallReservationDetails.getHallNUmber(),hallReservationDetails.getReservationId());
    }
}
