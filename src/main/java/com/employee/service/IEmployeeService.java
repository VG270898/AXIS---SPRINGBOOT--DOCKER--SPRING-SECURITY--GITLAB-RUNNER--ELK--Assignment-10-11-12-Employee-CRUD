package com.employee.service;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.AuthenticationRequest;
import com.employee.model.Employee;
import com.employee.model.EmployeeDTO;
import com.employee.model.EmployeeLogin;

import java.util.List;

public interface IEmployeeService {

    public EmployeeLogin registerCustomer(AuthenticationRequest authenticationRequest);
    public Employee addCustomerDetails(EmployeeDTO employeeDTO, String username);
    public List<Employee> findCustomerDetails(String username) throws EmployeeNotFoundException;
}
