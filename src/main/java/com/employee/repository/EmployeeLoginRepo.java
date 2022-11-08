package com.employee.repository;

import com.employee.model.EmployeeLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeLoginRepo extends JpaRepository<EmployeeLogin,Long> {
    EmployeeLogin findByUsername(String username);
}
