package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomReservationDTO {
    private String room_number;
    private String customer_id;
    private String reservation_id;
    private String check_out_date;
    private String check_in_date;

}
