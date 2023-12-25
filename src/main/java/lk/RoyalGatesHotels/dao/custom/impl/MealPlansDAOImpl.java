package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.MealPlansDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.HallMaintenance;
import lk.RoyalGatesHotels.dto.MealPackges;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealPlansDAOImpl implements MealPlansDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT pkg_id FROM mealpackages ORDER BY pkg_id DESC LIMIT 1";
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
            String newId = String.format("MP%04d", newNum);
            return newId;
        }
        return "MP0001";
    }

    @Override
    public boolean add(HallMaintenance entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO mealpackages(pkg_id, price, description, meal_plan, type) VALUES(?,?,?,?,?)";
        return SQLUtill.execute(sql, entity.getPkg_id(),entity.getPrice(), entity.getDescription(),entity.getMeal_plan(),entity.getType());
    }

    @Override
    public boolean update(MealPackges entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE mealpackages SET price=?, description=?, meal_plan=?, type=? WHERE pkg_id=?";
        return SQLUtill.execute(sql, entity.getPrice(), entity.getDescription(), entity.getMeal_plan(), entity.getType(), entity.getPkg_id());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM mealpackages WHERE pkg_id = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT pkg_id FROM mealpackages";
        List<String> packageIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            packageIds.add(resultSet.getString(1));
        }
        return packageIds;
    }

    @Override
    public MealPackges setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM mealpackages WHERE pkg_id=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String pkgId = resultSet.getString("pkg_id");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            String mealPlan = resultSet.getString("meal_plan");
            String type = resultSet.getString("type");

            return new MealPackges(pkgId, price, description, mealPlan, type);
        }
        return null;
    }

    @Override
    public List<MealPackges> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM mealpackages";
        List<MealPackges> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            String pkgId = resultSet.getString("pkg_id");
            double price = resultSet.getDouble("price");
            String description = resultSet.getString("description");
            String mealPlan = resultSet.getString("meal_plan");
            String type = resultSet.getString("type");

            MealPackges mealPackage = new MealPackges(pkgId, price, description, mealPlan, type);
            data.add(mealPackage);
        }
        return data;
    }

    @Override
    public String getItems(String packageId) throws SQLException, ClassNotFoundException {
        String items;
        String sql = "SELECT * FROM mealpackages WHERE pkg_id=?";
        ResultSet resultSet = SQLUtill.execute(sql, packageId);
        if (resultSet.next()){
            items = resultSet.getString("description");
            return items;
        }
        return null;
    }
}
