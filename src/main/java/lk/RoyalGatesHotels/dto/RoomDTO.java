package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomDTO {
    private String room_number;
    private String roomType;
    private String status;
    private double price;

}
