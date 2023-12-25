package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Complain;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintDAO extends CrudDAO <Complain> {
    public String getRIds() throws SQLException, ClassNotFoundException;
    public  List<String> getGIds() throws SQLException, ClassNotFoundException;
    public  List<String> getHIds() throws SQLException, ClassNotFoundException;
    public  List<String> getMIds() throws SQLException, ClassNotFoundException;
    public  List<Complain> getAll() throws SQLException, ClassNotFoundException;
    public Complain setFields(String complainid) throws SQLException, ClassNotFoundException;
}
