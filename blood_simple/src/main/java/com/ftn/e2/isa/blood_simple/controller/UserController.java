package com.ftn.e2.isa.blood_simple.controller;
import com.ftn.e2.isa.blood_simple.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class UserController {

    private UserService userService;
}
