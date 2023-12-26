package lk.RoyalGatesHotels.dto;

import lk.RoyalGatesHotels.entity.Complain;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ComplainDTO extends Complain {
    private String roomNumber;
    private String hallNumber;
    private String ComplainId;
    private String CustomerId;
    private String date;
    private String time;
    private String description;

}
