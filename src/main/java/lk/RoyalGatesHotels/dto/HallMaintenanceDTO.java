package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HallMaintenanceDTO {
    private String  maintenanceId;
    private String  hallNumber;
    private String  date;
    private String  startTime;
    private String  endTime;

}
