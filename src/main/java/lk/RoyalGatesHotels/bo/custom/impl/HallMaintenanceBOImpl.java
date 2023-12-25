package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.HallMaintenanceBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.HallMaintenanceDAO;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.Maintenance;

import java.sql.SQLException;
import java.util.List;

public class HallMaintenanceBOImpl implements HallMaintenanceBO {
    HallMaintenanceDAO hallMaintenanceDAO = (HallMaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.HALLMAINTENANCE);
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return hallMaintenanceDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("HM%04d", newNum);
            return newId;
        }
        return "HM0001";
    }

    @Override
    public boolean add(HallMaintenance dto) throws SQLException, ClassNotFoundException {
        return hallMaintenanceDAO.add(new HallMaintenance(dto.getMaintenanceId(),dto.getHallNumber(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));

    }

    @Override
    public boolean update(HallMaintenance dto) throws SQLException, ClassNotFoundException {
        return hallMaintenanceDAO.update(new HallMaintenance(dto.getMaintenanceId(),dto.getHallNumber(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return hallMaintenanceDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return hallMaintenanceDAO.getIds();
    }

    @Override
    public HallMaintenance setFields(String id) throws SQLException, ClassNotFoundException {
        HallMaintenance hallMaintenance = hallMaintenanceDAO.setFields(id);
        return new HallMaintenance(hallMaintenance.getMaintenanceId(),hallMaintenance.getHallNumber(),hallMaintenance.getDate(),hallMaintenance.getStartTime(),hallMaintenance.getEndTime());
    }
}
