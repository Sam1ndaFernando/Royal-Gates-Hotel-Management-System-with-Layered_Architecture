package lk.RoyalGatesHotels.dto.tm;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmployeeTM {
    private String employeeId;
    private String name;
    private String nic;
    private String address;
    private String mobile;
    private String email;
    private String joinDate;

    private String jobRole;

}

