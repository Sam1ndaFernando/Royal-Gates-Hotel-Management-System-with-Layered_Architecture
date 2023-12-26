package lk.RoyalGatesHotels.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class HallDTO {
    private String hallType;
    private String hallNumber;
    private String status;
    private Double price;

}
