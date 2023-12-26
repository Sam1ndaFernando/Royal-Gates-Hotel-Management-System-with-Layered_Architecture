package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MaintenanceDTO {
    private String maintenanceId;
    private String number;
    private String date;
    private String startTime;
    private String endTime;

}
