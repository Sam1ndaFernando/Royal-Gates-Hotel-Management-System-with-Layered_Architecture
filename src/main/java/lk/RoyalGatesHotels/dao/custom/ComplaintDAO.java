package lk.RoyalGatesHotels.dao.custom;

import lk.RoyalGatesHotels.dao.CrudDAO;
import lk.RoyalGatesHotels.dto.Complain;

import java.sql.SQLException;
import java.util.List;

public interface ComplaintDAO extends CrudDAO <Complain> {
    public List<String> getRIds() throws SQLException;
    public  List<String> getGIds() throws SQLException ;
    public  List<String> getHIds() throws SQLException ;
    public  List<String> getMIds() throws SQLException ;
    public  List<Complain> getAll() throws SQLException ;
    public Complain setFields(String complainid) throws SQLException, ClassNotFoundException;
}
