package com.employee.service;

import com.employee.model.EmployeeLogin;
import com.employee.repository.EmployeeLoginRepo;
import com.employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeLoginRepo employeeLoginRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EmployeeLogin employeeLogin = employeeLoginRepo.findByUsername(username);

        if(employeeLogin ==null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new PrincipalEmployee(employeeLogin);

    }

}



