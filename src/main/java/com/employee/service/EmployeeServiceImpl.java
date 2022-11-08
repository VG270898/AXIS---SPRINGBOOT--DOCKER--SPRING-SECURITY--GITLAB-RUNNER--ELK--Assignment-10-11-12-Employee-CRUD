package com.employee.service;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.AuthenticationRequest;
import com.employee.model.Employee;
import com.employee.model.EmployeeDTO;
import com.employee.model.EmployeeLogin;
import com.employee.repository.EmployeeLoginRepo;
import com.employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeLoginRepo employeeLoginRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeLogin registerCustomer(AuthenticationRequest authenticationRequest) {
//        authenticationRequest.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        EmployeeLogin employeeLogin = new EmployeeLogin();
        employeeLogin.setUsername(authenticationRequest.getUsername());
        employeeLogin.setPassword(authenticationRequest.getPassword());
        return employeeLoginRepo.save(employeeLogin);

    }

    @Override
    public Employee addCustomerDetails(EmployeeDTO employeeDTO, String username) {
        Employee employee = new Employee();
        employee.setCustomerId(employeeLoginRepo.findByUsername(username).getCustomerId());
        employee.setCustomerName(employeeDTO.getCustomerName());
        employee.setCustomerGender(employeeDTO.getCustomerGender());
        employee.setCustomerAge(employeeDTO.getCustomerAge());
        employee.setCustomerAddress(employeeDTO.getCustomerAddress());

        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> findCustomerDetails(String username) throws EmployeeNotFoundException {
        Long customerId = employeeLoginRepo.findByUsername(username).getCustomerId();
        System.out.println(customerId);
        if (employeeRepo.findById(customerId).isEmpty()) {
            throw new EmployeeNotFoundException("Customer Not Found with username " + username);
        } else {
            return employeeRepo.findById(customerId).stream().toList();
        }
    }
}
