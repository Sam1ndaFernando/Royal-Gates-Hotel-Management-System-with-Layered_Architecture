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

    public <T extends SuperBO>T getBO(BOTypes types){
        switch (types){
            case COMPLAINT:              return (T)  new ComplaintBOImpl();
            case DASHBOARD:              return (T)  new DashboardBOImpl();
            case EMPLOYEE:               return  (T) new EmployeeBOImpl();
            case GUEST:                  return (T)  new GuestBOImpl();
            case HALL:                   return (T)  new HallBOImpl();
            case HALLMAINTENANCE:        return (T)  new HallMaintenanceBOImpl();
            case HALLRESERVATION:        return (T)  new HallReservationBOImpl();
            case HALLRESERVATIONDETAILS: return  (T) new HallReservationDetailsBOImpl();
            case MEALORDER:              return (T)  new MealOrderBOImpl();
            case MEALORDERDETAILS:       return (T)  new MealOrderDetailsBOImpl();
            case MEALPLANS:              return (T)  new MealPlansBOImpl();
            case PAYMENT:                return (T)  new PaymentBOImpl();
            case ROOM:                   return  (T) new RoomBOImpl();
            case ROOMMAINTENANCE:        return (T)  new RoomMaintenanceBOImpl();
            case ROOMRESERVATION:        return (T)  new RoomReservationBOImpl();
            case ROOMRESERVATIONDETAILS: return (T)  new RoomReservationDetailsBOImpl();
            case USER:                   return (T)  new UserBOImpl();
            default:                     return null;
        }
    }

}
