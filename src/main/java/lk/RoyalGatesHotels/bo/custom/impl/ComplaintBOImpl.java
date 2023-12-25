package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.ComplaintBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.ComplaintDAO;
import lk.RoyalGatesHotels.dto.Complain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintBOImpl implements ComplaintBO {
    ComplaintDAO complaintDAO = (ComplaintDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMPLAINT);
    @Override
    public String getRIds() throws SQLException, ClassNotFoundException {
        return complaintDAO.getRIds();
    }

    @Override
    public List<String> getGIds() throws SQLException, ClassNotFoundException {
        return complaintDAO.getGIds();
    }

    @Override
    public List<String> getHIds() throws SQLException, ClassNotFoundException {
        return complaintDAO.getHIds();
    }

    @Override
    public List<String> getMIds() throws SQLException, ClassNotFoundException {
        return complaintDAO.getMIds();
    }

    @Override
    public List<Complain> getAll() throws SQLException, ClassNotFoundException {
        List<Complain> allEntityData = complaintDAO.getAll();
        List<Complain> data = new ArrayList<>();
        for (Complain complaint : allEntityData) {
            data.add(new Complain(complaint.getRoomNumber(),complaint.getHallNumber(),complaint.getComplainId(),complaint.getCustomerId(),complaint.getDate(),complaint.getTime(),complaint.getDescription()));
        }
        return data;
    }

    @Override
    public boolean add(Complain dto) throws SQLException, ClassNotFoundException {
        return complaintDAO.add(new Complain(dto.getRoomNumber(),dto.getHallNumber(),dto.getComplainId(),dto.getCustomerId(),dto.getDate(),dto.getTime(),dto.getDescription()));

    }

    @Override
    public boolean update(Complain dto) throws SQLException, ClassNotFoundException {
        return complaintDAO.update(new Complain(dto.getRoomNumber(),dto.getHallNumber(),dto.getComplainId(),dto.getCustomerId(),dto.getDate(),dto.getTime(),dto.getDescription()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return complaintDAO.delete(id);
    }

    @Override
    public Complain setFields(String id) throws SQLException, ClassNotFoundException {
        Complain complain = complaintDAO.setFields(id);
        return new Complain(complain.getRoomNumber(),complain.getHallNumber(),complain.getComplainId(),complain.getCustomerId(),complain.getDate(),complain.getTime(),complain.getDescription());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return complaintDAO.getNextId();
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return complaintDAO.getIds();
    }
}
