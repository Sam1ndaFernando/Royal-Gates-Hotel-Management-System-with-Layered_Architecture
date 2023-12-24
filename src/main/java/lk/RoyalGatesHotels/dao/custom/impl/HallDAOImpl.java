package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.HallDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.Hall;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAOImpl implements HallDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT hallNumber FROM halls ORDER BY hallNumber DESC LIMIT 1";
        ResultSet resultSet = SQLUtill.execute(sql);
        if(resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return splitId(null);
    }

    @Override
    public String splitId(String currentId) throws SQLException, ClassNotFoundException {
        if(currentId != null) {
            int lastNum = Integer.parseInt(currentId.substring(1));
            int newNum = lastNum + 1;
            String newId = String.format("H%04d", newNum);
            return newId;
        }
        return "H0001";
    }

    @Override
    public boolean add(Hall entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO halls(hallNumber, hall_type, status, price) VALUES(?,?,?,?)";
        return SQLUtill.execute(sql, entity.getHallNumber(),entity.getHallType(),entity.getStatus(),entity.getPrice());
    }

    @Override
    public boolean update(Hall entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE halls SET hall_type=?, price=?, status=? WHERE hallNumber=?";
        return SQLUtill.execute(sql, entity.getHallType(), entity.getPrice(), entity.getStatus(), entity.getHallNumber());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM halls WHERE hallNumber=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT hallNumber FROM halls";
        List<String> hallIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            hallIds.add(resultSet.getString(1));
        }
        return hallIds;    }

    @Override
    public Hall setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM halls WHERE hallNumber=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()){
            String hallNumber = resultSet.getString("hallNumber");
            String hallType = resultSet.getString("hall_type");
            String status = resultSet.getString("status");
            double price = resultSet.getDouble("price");
            return new Hall(hallNumber, hallType, status, price);
        }
        return  null;
    }
}
