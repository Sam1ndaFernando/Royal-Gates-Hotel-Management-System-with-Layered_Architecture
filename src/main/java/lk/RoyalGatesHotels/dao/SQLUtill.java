package lk.RoyalGatesHotels.dao;

import lk.RoyalGatesHotels.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtill {
    public static <T>T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1), args[i]);
        }

        if(sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) pstm.executeQuery(); // resultSet
        }
        return (T) (Boolean)(pstm.executeUpdate() > 0); //  boolean
    }
}
