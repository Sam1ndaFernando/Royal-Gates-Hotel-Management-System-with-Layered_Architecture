package lk.RoyalGatesHotels.dto.tm;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MealPackgesTM {
    private String pkg_id;
    private double price;
    private String description;
    private String meal_plan;
    private String type;
}