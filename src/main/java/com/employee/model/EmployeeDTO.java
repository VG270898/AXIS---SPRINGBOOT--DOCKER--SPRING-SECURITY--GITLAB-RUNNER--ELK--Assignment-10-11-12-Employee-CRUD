package com.employee.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private String customerName;
    private String customerGender;
    private int customerAge;
    private String customerAddress;


}
