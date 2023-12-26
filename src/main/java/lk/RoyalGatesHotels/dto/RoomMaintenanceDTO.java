package lk.RoyalGatesHotels.dto;

import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomMaintenanceDTO {
    private String maintenanceId;
    private String room_number;
    private String date;
    private String startTime;
    private String endTime;
}
