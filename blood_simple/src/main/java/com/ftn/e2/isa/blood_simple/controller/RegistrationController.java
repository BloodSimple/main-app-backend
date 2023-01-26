package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody Map<String, String> message, HttpServletRequest request) {
        boolean result = registrationService.registerRegularUser(message, getSiteURL(request));
        if (result) {
            return new ResponseEntity<>("Successfully registered. Verify your account before login.", HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong. Maybe a user with this email already exists.");
        }
    }

    @GetMapping("/verifyUserAccount")
    public String verifyUser(@Param("code") String code) {
        return registrationService.verifyRegularUserAccount(code) ? "User's account is successfully verified! :)" : "User's account verification failed! :(";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
