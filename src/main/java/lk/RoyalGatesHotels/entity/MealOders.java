package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MealOders {
    private String OrderId;
    private String CustomerId;
    private String Date;
    private int Qty;
    private String PkgId;

}
