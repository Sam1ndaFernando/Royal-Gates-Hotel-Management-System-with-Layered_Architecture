package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor

public class MealPackges {
    private String pkg_id;
    private double price;
    private String description;
    private String meal_plan;
    private String type;

}
