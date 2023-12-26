package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomMaintenance {
    private String maintenanceId;
    private String room_number;
    private String date;
    private String startTime;
    private String endTime;
}
