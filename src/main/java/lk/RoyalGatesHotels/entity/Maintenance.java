package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Maintenance {
    private String maintenanceId;
    private String number;
    private String date;
    private String startTime;
    private String endTime;

}
