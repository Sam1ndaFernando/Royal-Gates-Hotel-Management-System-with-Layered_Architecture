package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;

import java.sql.SQLException;

public interface MealOrderDetailsBO extends SuperBO {
    public  boolean add(String orderId, String packageId) throws SQLException, ClassNotFoundException;
    public  boolean delete(String orderId) throws SQLException, ClassNotFoundException;
    public  String getpkg(String cmbOrderId) throws SQLException, ClassNotFoundException;
}
