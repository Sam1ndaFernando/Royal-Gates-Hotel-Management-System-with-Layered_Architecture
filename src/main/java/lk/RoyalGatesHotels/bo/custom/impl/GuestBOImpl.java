package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.GuestBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.GuestDAO;
import lk.RoyalGatesHotels.dto.GuestDTO;
import lk.RoyalGatesHotels.entity.Guest;

import java.sql.SQLException;
import java.util.List;

public class GuestBOImpl implements GuestBO {
    GuestDAO guestDAO = (GuestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GUEST);
    @Override
    public String getName(String value) throws SQLException, ClassNotFoundException {
        return guestDAO.getNextId();
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return guestDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    @Override
    public boolean add(GuestDTO dto) throws SQLException, ClassNotFoundException {
        return guestDAO.add(new Guest(dto.getCustomerId(),dto.getCustomer_name(),dto.getDate(),dto.getNic(),dto.getAddress(),dto.getMobile(),dto.getEmail(),dto.getProvince()));

    }

    @Override
    public boolean update(GuestDTO dto) throws SQLException, ClassNotFoundException {
        return guestDAO.update(new Guest(dto.getCustomerId(),dto.getCustomer_name(),dto.getDate(),dto.getNic(),dto.getAddress(),dto.getMobile(),dto.getEmail(),dto.getProvince()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return guestDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return guestDAO.getIds();
    }

    @Override
    public GuestDTO setFields(String id) throws SQLException, ClassNotFoundException {
        Guest guest = guestDAO.setFields(id);
        return new GuestDTO(guest.getCustomerId(),guest.getCustomer_name(),guest.getDate(),guest.getNic(),guest.getAddress(),guest.getMobile(),guest.getEmail(),guest.getProvince());
    }
}
