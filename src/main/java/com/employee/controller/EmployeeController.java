package com.employee.controller;

import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.AuthenticationRequest;
import com.employee.model.AuthenticationResponse;
import com.employee.model.EmployeeDTO;
import com.employee.service.CustomUserDetailService;
import com.employee.service.EmployeeServiceImpl;
import com.employee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    AuthenticationResponse authenticationResponse= new AuthenticationResponse();
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("In customer authentication");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            LOG.log(Level.INFO,"Authentication Successfully");
        } catch (BadCredentialsException e) {
            LOG.log(Level.INFO,"Authentication Fail : " + e.getMessage());
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
//        System.out.println(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        LOG.log(Level.INFO,"Token Generated : "+ jwt);
        authenticationResponse.setJwt(jwt);
        return ResponseEntity.ok(authenticationResponse.getJwt());
    }

    @PostMapping("/register")
    public ResponseEntity registerCustomers(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        LOG.log(Level.INFO,"Registration done Successfully");
        return ResponseEntity.ok(employeeServiceImpl.registerCustomer(authenticationRequest));
    }

    @GetMapping("/getemployee")
    public ResponseEntity findCustomerByUsername() throws EmployeeNotFoundException {
        try{
            Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((UserDetails)auth).getUsername();
            return ResponseEntity.ok(employeeServiceImpl.findCustomerDetails(username));
        }catch (Exception e){
            throw new EmployeeNotFoundException(e.getMessage());
        }

    }

    @PostMapping("/addDetails")
    public ResponseEntity addCustomerDetails(@RequestBody @Valid EmployeeDTO employeeDTO){
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)auth).getUsername();
        return ResponseEntity.ok(employeeServiceImpl.addCustomerDetails(employeeDTO,username));
    }

}
