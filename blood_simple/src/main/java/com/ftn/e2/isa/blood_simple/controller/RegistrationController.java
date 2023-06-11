package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public boolean registerUser(@RequestBody Map<String, String> message, HttpServletRequest request) {
//        System.out.println("Pravi sifru");
//
//        User user = UserDTO.MapToUser(message);
//        System.out.println("Poslat korisnik sa sifrom:");
//        System.out.println(user.getPassword());
//        System.out.println("Pokusaj kodovanja:");
//        String sifra = passwordEncoder.encode("aA123456789!");
//        System.out.println(sifra);
//        return false;
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registrationService.registerRegularUser(message, getSiteURL(request));
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
