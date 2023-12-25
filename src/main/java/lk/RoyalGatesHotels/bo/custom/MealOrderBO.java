package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.MealOders;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface MealOrderBO extends SuperBO {
    public MealOders getFields(String id) throws SQLException, ClassNotFoundException;

    public  String getQty(String id) throws SQLException, ClassNotFoundException;

    public  boolean Order(String orderId, String guestId, String packageId, String date, int qty) throws SQLException ;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(MealOders dto) throws SQLException, ClassNotFoundException;

    public boolean update(MealOders dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public MealOders setFields(String id) throws SQLException, ClassNotFoundException ;
}
