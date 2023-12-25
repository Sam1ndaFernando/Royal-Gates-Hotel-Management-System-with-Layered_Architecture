package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.HallReservationBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.HallReservationDAO;
import lk.RoyalGatesHotels.dao.custom.HallReservationDetailsDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.HallReservation;
import lk.RoyalGatesHotels.dto.tm.HallReservationDetailTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HallReservationBOImpl implements HallReservationBO {
    HallReservationDAO hallReservationDAO = (HallReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATION);
    HallReservationDetailsDAO hallReservationDetailsDAO = (HallReservationDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLRESERVATIONDETAILS);
    @Override
    public boolean isValid(String hallNumber) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.isValid(hallNumber);
    }

    @Override
    public HallReservation setHFields(String hallnumber) throws SQLException, ClassNotFoundException {
        HallReservation hallReservation = hallReservationDAO.setHFields(hallnumber);
        return new HallReservation(hallReservation.getHall_number(),hallReservation.getCustomer_id(),hallReservation.getReservation_id(),hallReservation.getCheck_out_date(),hallReservation.getCheck_in_date());
    }

    @Override
    public boolean Order(String checkIn, String checkOut, String hallReservationId, String guestId, String hallNumber) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            boolean isSaved = hallReservationDAO.add(new HallReservation(hallNumber,guestId,hallReservationId,checkOut,checkIn));
            if (isSaved) {
                boolean isAdded = hallReservationDetailsDAO.add(new HallReservationDetailTM(hallReservationId, guestId, hallNumber));
                if (isAdded) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (con != null) {
                con.rollback();
            }
            return false;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return hallReservationDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("HR%04d", newNum);
            return newId;
        }
        return "HR0001";
    }

    @Override
    public boolean add(HallReservation dto) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.add(new HallReservation(dto.getHall_number(),dto.getCustomer_id(),dto.getReservation_id(),dto.getCheck_out_date(),dto.getCheck_in_date()));
    }

    @Override
    public boolean update(HallReservation dto) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.update(new HallReservation(dto.getHall_number(),dto.getCustomer_id(),dto.getReservation_id(),dto.getCheck_out_date(),dto.getCheck_in_date()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return hallReservationDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return hallReservationDAO.getIds();
    }

    @Override
    public HallReservation setFields(String id) throws SQLException, ClassNotFoundException {
        HallReservation hallReservation = hallReservationDAO.setFields(id);
        return new HallReservation(hallReservation.getHall_number(),hallReservation.getCustomer_id(),hallReservation.getReservation_id(),hallReservation.getCheck_out_date(),hallReservation.getCheck_in_date());
    }
}
