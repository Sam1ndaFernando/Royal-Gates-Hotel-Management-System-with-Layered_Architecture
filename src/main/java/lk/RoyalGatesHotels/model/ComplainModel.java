package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.dto.ComplainDTO;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplainModel {
    public static String getLastComplainId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT complaintId FROM complain ORDER BY complaintId DESC LIMIT 1");
        if (result.next()) {
            return result.getString("complaintId");
        }
        return null;
    }


    public static boolean addComplain(ComplainDTO complain) throws SQLException, ClassNotFoundException {
        boolean isAdded = CrudUtil.execute("INSERT INTO complain (room_number, hall_number, customer_id, date, time, description, complaintId) VALUES (?, ?, ?, ?, ?, ?, ?)",
                complain.getRoomNumber(),
                complain.getHallNumber(),
                complain.getCustomerId(),
                complain.getDate(),
                complain.getTime(),
                complain.getDescription(),
                complain.getComplainId()
        );
        return isAdded;
    }

    public static boolean updateComplain(ComplainDTO complain) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE complain SET room_number=?, hall_number=?, customer_id=?, date=?, time=?, description=? WHERE complaintId=?",
                complain.getRoomNumber(),
                complain.getHallNumber(),
                complain.getCustomerId(),
                complain.getDate(),
                complain.getTime(),
                complain.getDescription(),
                complain.getComplainId()
        );
        return isUpdate;
    }

    public static ResultSet searchComplain(String complainId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM complain WHERE complaintId=?", complainId);
        return result;
    }

    public static int getComplaintCount() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT COUNT(*) AS count FROM complain");
        int count = 0;
        if (result.next()) {
            count = result.getInt("count");
        }
        return count;
    }
}