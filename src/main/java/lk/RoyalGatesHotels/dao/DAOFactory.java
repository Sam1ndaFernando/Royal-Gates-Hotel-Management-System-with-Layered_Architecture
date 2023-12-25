package lk.RoyalGatesHotels.dao;

import lk.RoyalGatesHotels.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    public DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        COMPLAINT, DASHBOARD, EMPLOYEE, GUEST, HALL, HALLMAINTENANCE, HALLRESERVATION, HALLRESERVATIONDETAILS, MEALORDER, MEALORDERDETAILS, MEALPLANS, PAYMENT, ROOM, ROOMMAINTENANCE, ROOMRESERVATION, ROOMRESERVATIONDETAILS, USER, QUERY
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case COMPLAINT:
                return new ComplaintDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case GUEST:
                return new GuestDAOImpl();
            case HALL:
                return new HallDAOImpl();
            case HALLMAINTENANCE:
                return new HallMaintenanceDAOImpl();
            case HALLRESERVATION:
                return new HallReservationDAOImpl();
            case HALLRESERVATIONDETAILS:
                return new HallReservationDetailsDAOImpl();
            case MEALORDER:
                return new MealOrderDAOImpl();
            case MEALORDERDETAILS:
                return new MealOrderDetailsDAOImpl();
            case MEALPLANS:
                return new MealPlansDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case ROOMMAINTENANCE:
                return new RoomMaintenanceDAOImpl();
            case ROOMRESERVATION:
                return new RoomReservationDAOImpl();
            case ROOMRESERVATIONDETAILS:
                return new RoomReservationDetailsDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }

}
