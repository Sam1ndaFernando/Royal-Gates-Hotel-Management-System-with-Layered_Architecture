package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.MealOders;


import java.sql.SQLException;

public interface MealOrderDAO extends CrudDAO<MealOders> {

    public MealOders getFields(String id) throws SQLException ;

    public  String getQty(String value) throws SQLException ;

 //   public  boolean Order(String orderId, String guestId, String packageId, String date, String qty, String orderId1, String packageId1) throws SQLException ;

}
