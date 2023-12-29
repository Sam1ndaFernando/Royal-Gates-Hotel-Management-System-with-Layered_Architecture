package lk.RoyalGatesHotels.dao.custom.impl;


import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.ComplaintDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.ComplainDTO;
import lk.RoyalGatesHotels.entity.Complain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAOImpl implements ComplaintDAO {


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT complaintId FROM complain ORDER BY complaintId DESC LIMIT 1";
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
    public boolean add(Complain entity) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO complain (room_number, hall_number, complaintId, customer_id, date, time, description) VALUES(?, ?, ?, ?, ?, ?, ?)";
        return SQLUtill.execute(sql, entity.getRoomNumber(), entity.getHallNumber(), entity.getComplainId(), entity.getCustomerId(), entity.getDate(), entity.getTime(), entity.getDescription());

    }

    @Override
    public boolean update(Complain entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE complain SET Date = ?, time = ?, room_number = ?, hall_number = ?, description = ? WHERE complaintId = ?";
        return SQLUtill.execute(sql, entity.getDate(), entity.getTime(), entity.getRoomNumber(), entity.getHallNumber(), entity.getDescription(), entity.getComplainId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM complain WHERE complaintId = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getRIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT reservation_id FROM roomreservationdetail";
        List<String> RIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            RIds.add(resultSet.getString(1));
        }
        return RIds;
    }

    @Override
    public List<String> getGIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT customerId FROM customer";
        List<String> GIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            GIds.add(resultSet.getString(1));
        }
        return GIds;
    }

    @Override
    public List<String> getHIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT reservationId FROM hallreservationdetail";
        List<String> HIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            HIds.add(resultSet.getString(1));
        }
        return HIds;
    }

    @Override
    public List<String> getMIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT oder_id FROM meal_oders";
        List<String> MIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            MIds.add(resultSet.getString(1));
        }
        return MIds;
    }

    @Override
    public List<Complain> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM complain";
        List<Complain> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new ComplainDTO(
                    resultSet.getString("room_number"),
                    resultSet.getString("hall_number"),
                    resultSet.getString("complaintId"),
                    resultSet.getString("customer_id"),
                    resultSet.getString("date"),
                    resultSet.getString("time"),
                    resultSet.getString("description")
            ));
        }
        return data;
    }

    @Override
    public Complain setFields(String complainId) throws SQLException, ClassNotFoundException {
//        String sql = "SELECT * FROM complaints WHERE ComplaintId = ?";
//
//        try (ResultSet resultSet = SQLUtill.execute(sql, complainId)) {
//            if (resultSet.next()) {
//                String complaintId = resultSet.getString("complaintId");
//                String date = resultSet.getString("date");
//                String time = resultSet.getString("time");
//                String customer_id = resultSet.getString("customer_id");
//                String hallReservationId = resultSet.getString("hall_number");
//                String roomReservationId = resultSet.getString("room_number");
//                String description = resultSet.getString("description");
//
//                return new Complain(complaintId, date, time, customer_id, hallReservationId, roomReservationId, description);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Complain;

        String sql = "SELECT * FROM complain";
        List<ComplainDTO> data = new ArrayList<>();
        ResultSet resultSet = SQLUtill.execute(sql);
        while (resultSet.next()) {
            data.add(new ComplainDTO(
                    resultSet.getString("room_number"),
                    resultSet.getString("hall_number"),
                    resultSet.getString("complaintId"),
                    resultSet.getString("customer_id"),
                    resultSet.getString("date"),
                    resultSet.getString("time"),
                    resultSet.getString("description")
            ));
        }
        return (Complain) data;

    }

}
