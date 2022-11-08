package com.employee.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@SequenceGenerator(name = "custom_id",sequenceName = "custom_id",initialValue = 101)
public class EmployeeLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "custom_id")
    private Long customerId;

    @NotNull(message = "Enter UserName")
    private String username;

    @NotNull(message = "Enter Password")
    private String password;

    public String getUsername() {
        return username;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // need default constructor for JSON Parsing
    public EmployeeLogin() {

    }

    public EmployeeLogin(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
}
