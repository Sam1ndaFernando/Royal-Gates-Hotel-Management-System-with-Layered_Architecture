package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Guest;

import java.sql.SQLException;

public interface GuestDAO extends CrudDAO <Guest> {
    public  String getName(String value) throws SQLException;
}