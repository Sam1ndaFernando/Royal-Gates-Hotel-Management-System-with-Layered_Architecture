package lk.RoyalGatesHotels.bo.custom.impl;

import lk.RoyalGatesHotels.bo.custom.DashboardBO;
import lk.RoyalGatesHotels.dao.DAOFactory;
import lk.RoyalGatesHotels.dao.custom.DashboardDAO;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {
    DashboardDAO dashboardDAO = (DashboardDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DASHBOARD);
    @Override
    public int getTotalRooms() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalRooms();
    }

    @Override
    public int getTotalHalls() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getTotalHalls();
    }

    @Override
    public int getBookedHalls() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getBookedHalls();
    }

    @Override
    public int getBookedRooms() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getBookedRooms();
    }

    @Override
    public int getComplaints() throws SQLException, ClassNotFoundException {
        return dashboardDAO.getComplaints();
    }
}
