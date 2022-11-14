package com.ftn.e2.isa.blood_simple.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.service.SystemAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/sysadmin")
public class SystemAdminController {
	
	@Autowired
	SystemAdminService service;
	
	@GetMapping(value = "/", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllSystemAdmins(HttpServletRequest request){
		List<User> admins = service.getAllSystemAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getSystemAdmin(@PathVariable String id,HttpServletRequest request){
		User admin = service.getSystemAdmin(id);
		if (admin.equals(null))
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addSystemAdmin(@RequestBody User admin, HttpServletRequest request){
		User a = service.saveOrUpdateSystemAdmin(admin);
		if (a.equals(null))
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.OK);
	}
	
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable String id){
        service.delete(id);
    }
}
