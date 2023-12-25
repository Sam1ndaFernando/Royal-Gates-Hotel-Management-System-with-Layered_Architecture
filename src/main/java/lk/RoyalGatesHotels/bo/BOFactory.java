package lk.RoyalGatesHotels.bo;

import lk.RoyalGatesHotels.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        COMPLAINT, DASHBOARD, EMPLOYEE, GUEST, HALL, HALLMAINTENANCE, HALLRESERVATION, HALLRESERVATIONDETAILS, MEALORDER, MEALORDERDETAILS, MEALPLANS, PAYMENT, ROOM, ROOMMAINTENANCE, ROOMRESERVATION, ROOMRESERVATIONDETAILS, USER
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case COMPLAINT:              return new ComplaintBOImpl();
            case DASHBOARD:              return new DashboardBOImpl();
            case EMPLOYEE:               return new EmployeeBOImpl();
            case GUEST:                  return new GuestBOImpl();
            case HALL:                   return new HallBOImpl();
            case HALLMAINTENANCE:        return new HallMaintenanceBOImpl();
            case HALLRESERVATION:        return new HallReservationBOImpl();
            case HALLRESERVATIONDETAILS: return new HallReservationDetailsBOImpl();
            case MEALORDER:              return new MealOrderBOImpl();
            case MEALORDERDETAILS:       return new MealOrderDetailsBOImpl();
            case MEALPLANS:              return new MealPlansBOImpl();
            case PAYMENT:                return new PaymentBOImpl();
            case ROOM:                   return new RoomBOImpl();
            case ROOMMAINTENANCE:        return new RoomMaintenanceBOImpl();
            case ROOMRESERVATION:        return new RoomReservationBOImpl();
            case ROOMRESERVATIONDETAILS: return new RoomReservationDetailsBOImpl();
            case USER:                   return new UserBOImpl();
            default:                     return null;
        }
    }

}
