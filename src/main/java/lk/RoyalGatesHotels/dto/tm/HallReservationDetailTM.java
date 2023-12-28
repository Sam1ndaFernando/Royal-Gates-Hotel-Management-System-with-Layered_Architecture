package lk.RoyalGatesHotels.dto.tm;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HallReservationDetailTM {
    private String hallNUmber;
    private String customerId;
    private String reservationId;
    private String checkOutDate;
    private String checkInDate;

}