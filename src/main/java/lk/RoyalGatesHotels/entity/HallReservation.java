package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HallReservation {
    private String hall_number;
    private String customer_id;
    private String reservation_id;
    private String check_out_date;
    private String check_in_date;

}
