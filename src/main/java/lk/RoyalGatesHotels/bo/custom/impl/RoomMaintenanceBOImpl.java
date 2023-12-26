package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.RoomMaintenanceBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.RoomMaintenanceDAO;
import lk.RoyalGatesHotels.dto.MaintenanceDTO;
import lk.RoyalGatesHotels.entity.RoomMaintenance;

import java.sql.SQLException;
import java.util.List;

public class RoomMaintenanceBOImpl implements RoomMaintenanceBO {
    RoomMaintenanceDAO roomMaintenanceDAO = (RoomMaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOMMAINTENANCE);

    @Override
    public boolean updateRoom(String maintenanceId, String roomNumber, String date, String startTime, String endTime) throws SQLException {
        return  roomMaintenanceDAO.update(maintenanceId,roomNumber,date,startTime,endTime);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.getNextId();
    }

    @Override
    public String splitId(String id) throws SQLException, ClassNotFoundException {
        if(id != null) {
            int lastNum = Integer.parseInt(id.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("RM%04d", newNum);
            return newId;
        }
        return "RM0001";
    }

    @Override
    public boolean add(MaintenanceDTO dto) throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.add(new roommaintenance(dto.getMaintenanceId(),dto.getNumber(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));
    }

    @Override
    public boolean update(MaintenanceDTO dto) throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.update(new roommaintenance(dto.getMaintenanceId(),dto.getNumber(),dto.getDate(),dto.getStartTime(),dto.getEndTime()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return roomMaintenanceDAO.delete(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public MaintenanceDTO setFields(String id) throws SQLException, ClassNotFoundException {
        RoomMaintenance roommaintenance = roomMaintenanceDAO.setFields(id);
        return new MaintenanceDTO(roommaintenance.getMaintenanceId(),roommaintenance.getNumber(),roommaintenance.getDate(),roommaintenance.getStartTime(),roommaintenance.getEndTime());
    }
}
