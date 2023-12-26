package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Complain {
    private String roomNumber;
    private String hallNumber;
    private String ComplainId;
    private String CustomerId;
    private String date;
    private String time;
    private String description;

}
