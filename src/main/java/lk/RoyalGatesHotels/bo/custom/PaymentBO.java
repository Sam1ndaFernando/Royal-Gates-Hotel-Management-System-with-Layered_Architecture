package lk.RoyalGatesHotels.bo.custom;

import lk.RoyalGatesHotels.bo.SuperBO;
import lk.RoyalGatesHotels.dto.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public  double generateTotValue() throws SQLException, ClassNotFoundException;

    public List<String> getGIds() throws SQLException, ClassNotFoundException;

    public  List<String> getOIds() throws SQLException, ClassNotFoundException;

    public  List<String> getHIds() throws SQLException, ClassNotFoundException;

    public  List<String> getRds() throws SQLException, ClassNotFoundException;
    public String getNextId() throws SQLException, ClassNotFoundException;

    public  String splitId(String id) throws SQLException, ClassNotFoundException;

    public boolean add(Payment dto) throws SQLException, ClassNotFoundException;

    public boolean update(Payment dto) throws SQLException, ClassNotFoundException;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public Payment setFields(String id) throws SQLException, ClassNotFoundException ;
}
