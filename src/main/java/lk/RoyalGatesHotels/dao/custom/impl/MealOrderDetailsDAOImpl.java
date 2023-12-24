package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.MealOrderDetailsDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MealOrderDetailsDAOImpl implements MealOrderDetailsDAO {
    @Override
    public boolean add(String orderId, String packageId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO meal_oders(pkg_id, oder_id)VALUES(?, ?)";
        return SQLUtill.execute(sql, packageId,orderId);
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM meal_oders WHERE oder_id=?";
        return SQLUtill.execute(sql, orderId);
    }

    @Override
    public String getpkg(String cmbOrderId) throws SQLException, ClassNotFoundException {
        String pkgId;
        String sql = "SELECT * FROM meal_oders WHERE oder_id=?";
        ResultSet resultSet = SQLUtill.execute(sql, cmbOrderId);
        if (resultSet.next()){
            pkgId= resultSet.getString("PackageId");
            return pkgId;
        }
        return null;
    }
}
