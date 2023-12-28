package lk.RoyalGatesHotels.dto.tm;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RoomReservationDetailTM {
    private String roomNumber;
    private String CustomerId;
    private String reservationId;
    private String checkOutDate;
    private String checkInDate;

}