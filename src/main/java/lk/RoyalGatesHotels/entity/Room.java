package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Room {
    private String room_number;
    private String roomType;
    private String status;
    private double price;

}
