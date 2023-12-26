package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MealOdersDTO {
    private String OrderId;
    private String CustomerId;
    private String Date;
    private int Qty;
    private String PkgId;

}
