package lk.RoyalGatesHotels.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.RoyalGatesHotels.dto.GuestDTO;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestModel {
    public static String getLastGuestId() throws SQLException, ClassNotFoundException {
        String query = "SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1";

        try (ResultSet result = CrudUtil.execute(query)) {
            if (result.next()) {
                return result.getString("customerId");
            }
        }

        return null;
    }
    public static boolean addGuest(GuestDTO guest) throws SQLException, ClassNotFoundException {

        boolean isAdd = CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?,?,?,?)",
                guest.getCustomerId(),
                guest.getCustomer_name(),
                guest.getDate(),
                guest.getNic(),
                guest.getAddress(),
                guest.getMobile(),
                guest.getEmail(),
                guest.getProvince()
                );
        return isAdd;

    }

    public static boolean updateGuest(GuestDTO guest) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE customer SET customer_name=?, date=?, nic=?, address=?, mobile=?, email=?, province=? WHERE customerId=?",
                guest.getCustomer_name(),
                guest.getDate(),
                guest.getNic(),
                guest.getAddress(),
                guest.getMobile(),
                guest.getEmail(),
                guest.getProvince(),
                guest.getCustomerId()

        );
        return isUpdate;
    }

    public static ResultSet searchGuest(String guestId) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM customer WHERE customerId=?",guestId);
        return result;

    }

    public static ObservableList<String> loadGuestIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

        ObservableList<String> options = FXCollections.observableArrayList();
        while (resultSet.next()){
            options.add(resultSet.getString("customerId"));
        }
        return options;
    }
}
