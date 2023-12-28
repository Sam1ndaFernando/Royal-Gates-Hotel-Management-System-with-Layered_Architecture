package lk.RoyalGatesHotels.dao.custom.impl;

import lk.RoyalGatesHotels.dao.SQLUtill;
import lk.RoyalGatesHotels.dao.custom.RoomDAO;
import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.entity.Hall;
import lk.RoyalGatesHotels.entity.Room;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT roomNumber FROM room ORDER BY roomNumber DESC LIMIT 1";
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
            String newId = String.format("R%04d", newNum);
            return newId;
        }
        return "R0001";
    }

    @Override
    public boolean add(Room entity) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO room(roomNumber, room_type, status, price) VALUES(?,?,?,?)";
        return SQLUtill.execute(sql, entity.getRoom_number(), entity.getRoomType(), entity.getStatus(), entity.getPrice());
    }

    @Override
    public boolean update(Room entity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE room SET room_type=?, status=?, price=? WHERE roomNumber=?";
        return SQLUtill.execute(sql, entity.getRoomType(), entity.getStatus(), entity.getPrice(), entity.getRoom_number());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM room WHERE roomNumber = ?";
        return SQLUtill.execute(sql, id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT roomNumber FROM room";
        List<String> empIds = new ArrayList<>();
        ResultSet resultSet=con.createStatement().executeQuery(sql);
        while(resultSet.next()){
            empIds.add(resultSet.getString(1));
        }
        return empIds;
    }

    @Override
    public Room setFields(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM room WHERE RoomNumber = ?";
        ResultSet resultSet = SQLUtill.execute(sql, id);
        if (resultSet.next()) {
            String roomNumber = resultSet.getString("roomNumber");
            String roomType = resultSet.getString("room_type");
            String status = resultSet.getString("status");
            double price = resultSet.getDouble("price");
            return new Room(roomNumber, roomType, status, price);
        }
        return null;
    }

    @Override
    public List<Room> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM room");
        List<Room> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Room(
                    rst.getString("roomNumber"),
                    rst.getString("room_type"),
                    rst.getString("status"),
                    rst.getDouble("price")
            ));
        }
        return list;
    }
}
