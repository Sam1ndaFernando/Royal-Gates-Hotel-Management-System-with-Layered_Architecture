package lk.RoyalGatesHotels.entity;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Guest {
    private String customerId;
    private String customer_name;
    private String date;
    private String nic;
    private String address;
    private String mobile;
    private String email;
    private String province;
}
