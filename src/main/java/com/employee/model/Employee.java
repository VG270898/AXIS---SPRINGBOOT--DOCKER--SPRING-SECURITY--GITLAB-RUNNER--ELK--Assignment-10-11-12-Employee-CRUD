package com.employee.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
    @Id
    @NotNull(message = "Enter a valid customerId")
    private Long customerId;

    @NotNull(message = "Enter Customer Name")
    private String customerName;

    @NotNull(message = "Enter Customer Gender")
    private String customerGender;

    @NotNull(message = "Enter Customer Age")
    @Min(18)
    @Max(60)
    private int customerAge;

    @NotBlank(message = "Enter CustomerAddress")
    private String customerAddress;



}
