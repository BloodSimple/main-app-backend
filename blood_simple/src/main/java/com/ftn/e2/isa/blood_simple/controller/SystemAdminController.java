package com.ftn.e2.isa.blood_simple.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.security.authentication.JwtAuthenticationRequest;
import com.ftn.e2.isa.blood_simple.service.SystemAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/sysadmin")

public class SystemAdminController {
	
	@Autowired
	SystemAdminService systemAdminService;

	@Autowired
	RegistrationService registrationService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/", produces= MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('MEDICAL_ADMIN')")

	public ResponseEntity<List<User>> getAllSystemAdmins(HttpServletRequest request){
		List<User> admins = systemAdminService.getAllSystemAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	
	
    @PutMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUserPass(@RequestBody JwtAuthenticationRequest login){
       User user = systemAdminService.getSystemAdminByMail(login.getEmail());
       user.setPassword(passwordEncoder.encode(login.getPassword()));
       user.setFirst_login(false);
       User user1 = systemAdminService.saveOrUpdateSystemAdmin(user);
       if(user1 != null){
           return new ResponseEntity<>(user1, HttpStatus.OK);
       }else {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
   // @PreAuthorize("hasRole('MEDICAL_ADMIN')")

	public ResponseEntity<User> getSystemAdmin(@PathVariable String id,HttpServletRequest request){
		User admin = systemAdminService.getSystemAdmin(id);
		if (admin.equals(null))
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@GetMapping(value="/mail/{email}", produces=MediaType.APPLICATION_JSON_VALUE)
	   // @PreAuthorize("hasRole('MEDICAL_ADMIN')")

		public User getSystemAdminByEmail(@PathVariable String email,HttpServletRequest request){
			User admin = systemAdminService.getSystemAdminByMail(email);
			if (admin.equals(null))
				return null;
			return admin;
		}
	
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
  //  @PreAuthorize("hasRole('MEDICAL_ADMIN')")

	public ResponseEntity<User> addSystemAdmin(@RequestBody User admin, HttpServletRequest request){
		// TO JE BILO -- User a = service.saveOrUpdateSystemAdmin(admin);
		boolean successfullyRegistered = registrationService.registerSystemAdmin(admin, getSiteURL(request));
		if(!successfullyRegistered){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
    @DeleteMapping(value = "/{id}")
  //  @PreAuthorize("hasRole('MEDICAL_ADMIN')")

    public void deleteUser(@PathVariable String id){
		systemAdminService.delete(id);
    }

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
