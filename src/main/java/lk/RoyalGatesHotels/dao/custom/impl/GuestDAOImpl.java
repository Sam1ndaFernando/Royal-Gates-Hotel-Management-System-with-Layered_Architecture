package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.GuestDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.GuestDTO;
import lk.RoyalGatesHotels.dto.HallMaintenanceDTO;
import lk.RoyalGatesHotels.entity.Guest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestDAOImpl implements GuestDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1";
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
            String newId = String.format("C%04d", newNum);
            return newId;
        }
        return "C0001";
    }

    @Override
    public boolean add(Guest entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO customer(customerId, customer_name, date, nic, address, Mobile, Email, Province) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getCustomerId(),entity.getCustomer_name(), entity.getDate(),entity.getNic(),entity.getAddress(), entity.getMobile(), entity.getEmail(), entity.getProvince());
    }

    @Override
    public boolean update(Guest entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE customer SET customer_name=?, date=?, nic=?, address=?, Mobile=?, Email=?, Province=? WHERE customerId=?";
        return SQLUtill.execute(sql, entity.getCustomer_name(), entity.getDate(), entity.getNic(), entity.getAddress(), entity.getMobile(), entity.getEmail(), entity.getProvince(), entity.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM customer WHERE customerId=?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT customerId FROM customer";
        List<String> guestIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            guestIds.add(resultSet.getString(1));
        }
        return guestIds;
    }

    @Override
    public Guest setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer WHERE customerId=?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if(resultSet.next()) {
            String customerId = resultSet.getString("customerId");
            String customer_name = resultSet.getString("customer_name");
            String date = String.valueOf(resultSet.getDate("date"));
            String nic = resultSet.getString("nic");
            String address = resultSet.getString("address");
            String mobile = resultSet.getString("Mobile");
            String email = resultSet.getString("Email");
            String province = resultSet.getString("Province");

            return new Guest(customerId, customer_name, date, nic, address, mobile, email, province);
        }
        return null;
    }

    @Override
    public String getName(String value) throws SQLException, ClassNotFoundException {
        String name;
        String sql ="SELECT * FROM customer WHERE customerId=?";
        ResultSet resultSet = SQLUtill.execute(sql, value);
        if(resultSet.next()){
            name = resultSet.getString("customer_name");
        }else{
            name = "Nothing Found";
        }
        return name;
    }
}
