package lk.RoyalGatesHotels.dto;

public class HallMaintenance {
    private String  maintenanceId;
    private String  hallNumber;
    private String  date;
    private String  startTime;
    private String  endTime;

    public HallMaintenance() {
    }

    public HallMaintenance(String maintenanceId, String hallNumber, String date, String startTime, String endTime) {
        this.maintenanceId = maintenanceId;
        this.hallNumber = hallNumber;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(String maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "hallMaintenance{" +
                "maintenanceId='" + maintenanceId + '\'' +
                ", hallNumber='" + hallNumber + '\'' +
                ", date='" + date + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
