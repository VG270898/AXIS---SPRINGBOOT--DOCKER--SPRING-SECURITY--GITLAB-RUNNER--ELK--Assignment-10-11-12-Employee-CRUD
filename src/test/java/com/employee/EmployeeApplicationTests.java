package com.employee;

import com.employee.model.AuthenticationRequest;
import com.employee.model.EmployeeLogin;
import com.employee.repository.EmployeeLoginRepo;
import com.employee.repository.EmployeeRepo;
import com.employee.service.CustomUserDetailService;
import com.employee.service.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private EmployeeLoginRepo employeeLoginRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Test
	public void registerCustomerTest(){
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("vishal12345","vishal@123");
		EmployeeLogin employeeLogin = new EmployeeLogin();
		employeeLogin.setPassword(authenticationRequest.getPassword());
		employeeLogin.setUsername(authenticationRequest.getUsername());
		employeeLoginRepo.save(employeeLogin);
		assertNotNull(employeeLoginRepo.findByUsername("vishal12345")); //Passed Successfully

	}


}
