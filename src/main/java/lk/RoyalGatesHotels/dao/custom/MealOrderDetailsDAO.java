package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dao.SuperDAO;

import java.sql.SQLException;

public interface MealOrderDetailsDAO extends SuperDAO {

    public  boolean add(String orderId, String packageId) throws SQLException ;
    public  boolean delete(String orderId) throws SQLException ;
    public  String getpkg(String cmbOrderId) throws SQLException ;

}
