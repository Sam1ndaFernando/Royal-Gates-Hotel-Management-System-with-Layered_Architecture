package lk.RoyalGatesHotels.model;

import lk.RoyalGatesHotels.db.DBConnection;
import lk.RoyalGatesHotels.dto.MaintenanceDTO;
import lk.RoyalGatesHotels.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaintenanceModel {
    private static Connection connection;

    public static String getLastRoomMaintenanceId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM roomMaintenance ORDER BY maintenanceId DESC LIMIT 1");
        if(result.next()){
            return result.getString("maintenanceId");
        }
        return null;
    }

    public static String getLastHallMaintenanceId() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM hallMaintenance ORDER BY maintenanceId DESC LIMIT 1");
        if(result.next()){
            return result.getString("maintenanceId");
        }
        return null;
    }

    public static boolean addRoomMaintenance(MaintenanceDTO maintenance) throws SQLException, ClassNotFoundException {

        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isAdd = CrudUtil.execute("INSERT INTO roomMaintenance VALUES(?,?,?,?,?)",
                    maintenance.getMaintenanceId(),
                    maintenance.getNumber(),
                    maintenance.getDate(),
                    maintenance.getStartTime(),
                    maintenance.getEndTime()
            );
            if (isAdd){
                boolean isSetAvailability = RoomsModel.updateRoomAvailability(maintenance.getNumber(),"Unavailable");

                if(isSetAvailability){
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }


    }

    public static boolean addHallMaintenance(MaintenanceDTO maintenance) throws SQLException, ClassNotFoundException {
        boolean isAdd = CrudUtil.execute("INSERT INTO hallMaintenance VALUES(?,?,?,?,?)",
                maintenance.getMaintenanceId(),
                maintenance.getNumber(),
                maintenance.getDate(),
                maintenance.getStartTime(),
                maintenance.getEndTime()
        );
        return isAdd;
    }

    public static boolean updateRoomMaintenance(MaintenanceDTO maintenance, Connection connection) throws SQLException, ClassNotFoundException {

        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isUpdate = CrudUtil.execute("UPDATE roomMaintenance SET room_number=?, date=?, start_time=?, end_time=? WHERE maintenanceId=?",
                    maintenance.getNumber(),
                    maintenance.getDate(),
                    maintenance.getStartTime(),
                    maintenance.getEndTime(),
                    maintenance.getMaintenanceId()
            );
            if (isUpdate){
                boolean isSetAvailability = RoomsModel.updateRoomAvailability(maintenance.getNumber(),"Unavailable");

                if(isSetAvailability){
                    DBConnection.getInstance().getConnection().commit();
                    return true;
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        }finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }


    }

    public static boolean updateHallMaintenance(MaintenanceDTO maintenance) throws SQLException, ClassNotFoundException {
        boolean isUpdate = CrudUtil.execute("UPDATE hallMaintenance SET hall_number=?, date=?, start_time=?, end_time=? WHERE maintenanceId=?",
                maintenance.getNumber(),
                maintenance.getDate(),
                maintenance.getStartTime(),
                maintenance.getEndTime(),
                maintenance.getMaintenanceId()
        );
        return isUpdate;

    }
}
