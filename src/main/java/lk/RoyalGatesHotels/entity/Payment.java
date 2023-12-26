package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Payment{
    private String paymentId;
    private String reservationId;
    private String time;
    private String date;
    private String OrderId;
    private String customerId;
    private String qty;

   /* private String hallReservationId;
    private String roomReservationId;
    private String amount;*/
}