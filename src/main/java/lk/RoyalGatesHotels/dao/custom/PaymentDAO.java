package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Payment;

import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends CrudDAO <Payment> {

    public  double generateTotValue() throws SQLException, ClassNotFoundException;

    public  List<String> getGIds() throws SQLException, ClassNotFoundException;

    public  List<String> getOIds() throws SQLException, ClassNotFoundException;

    public  List<String> getHIds() throws SQLException, ClassNotFoundException;

    public  List<String> getRds() throws SQLException, ClassNotFoundException;

}
