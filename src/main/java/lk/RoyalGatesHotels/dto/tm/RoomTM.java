package lk.RoyalGatesHotels.dto.tm;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomTM {
    private String roomNumber;
    private String roomType;
    private String status;
    private String price;

}