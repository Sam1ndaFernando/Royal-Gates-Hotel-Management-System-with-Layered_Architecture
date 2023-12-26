package lk.RoyalGatesHotels.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public class HallReservationDetailsDTO {
        private String hall_number;
        private String customer_id;
        private String reservation_id;
        private String check_out_date;
        private String check_in_date;
    }

