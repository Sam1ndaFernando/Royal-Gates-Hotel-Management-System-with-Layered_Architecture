package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PaymentDTO {
    private String paymentId;
    private String hallReservationId;
    private String roomReservationId;
    private String time;
    private String date;
    private String OrderId;
    private String customerId;
    private String amount;
    private String reservationId;
    private String qty;

}