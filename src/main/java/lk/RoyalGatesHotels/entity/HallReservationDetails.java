package lk.RoyalGatesHotels.entity;

import lombok.*;
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public class HallReservationDetails {
        private String hall_number;
        private String customer_id;
        private String reservation_id;
        private String check_out_date;
        private String check_in_date;
    }

