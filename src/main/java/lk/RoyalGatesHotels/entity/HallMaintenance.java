package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HallMaintenance {
    private String  maintenanceId;
    private String  hallNumber;
    private String  date;
    private String  startTime;
    private String  endTime;

}
