package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.MealOrderDAO;
import lk.RoyalGatesHotels.dto.MealOders;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MealOrderDAOImpl implements MealOrderDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT oder_id FROM meal_oders ORDER BY oder_id DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(2));
            int newNum = lastNum + 1;
            String newId = String.format("MO%04d", newNum);
            return newId;
        }
        return "MO0001";
    }

    @Override
    public boolean add(MealOders entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO meal_oders(oder_id, customer_id, date, qty, pkg_id)VALUES(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getOrderId(),entity.getCustomerId(),entity.getPkgId(),entity.getQty(),entity.getDate());
    }

    @Override
    public boolean update(MealOders entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE meal_oders SET customer_id=?, date=?, qty=?, pkg_id=? WHERE oder_id=?";
        return SQLUtill.execute(sql, entity.getCustomerId(), entity.getDate(), entity.getQty(), entity.getPkgId(), entity.getOrderId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM meal_oders WHERE oder_id=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public MealOders setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal_oders WHERE oder_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String orderId = resultSet.getString("oder_id");
            String customerId = resultSet.getString("customer_id");
            String date = resultSet.getString("date");
            int qty = resultSet.getInt("qty");
            String pkgId = resultSet.getString("pkg_id");

            return new MealOders(orderId,customerId,date,qty,pkgId);
        }
        return null;
    }

    @Override
    public MealOders getFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM meal_oders WHERE oder_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);

        if (resultSet.next()) {
            String orderId = resultSet.getString("oder_id");
            String customerId = resultSet.getString("customer_id");
            String date = resultSet.getString("date");
            int qty = resultSet.getInt("qty");
            String pkgId = resultSet.getString("pkg_id");

            return new MealOders(orderId, customerId, date, qty, pkgId);
        }
        return null;
    }
    @Override
    public String getQty(String value) throws SQLException, ClassNotFoundException {
        String qty;
        String sql = "SELECT * FROM meal_oders WHERE oder_id = ?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if (resultSet.next()){
            qty = resultSet.getString("Qty");
            return qty;
        }
        return null;
    }
}
