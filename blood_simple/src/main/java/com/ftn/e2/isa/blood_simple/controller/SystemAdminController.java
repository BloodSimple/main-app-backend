package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import com.ftn.e2.isa.blood_simple.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/sysadmin")

public class SystemAdminController {

    @Autowired
    SystemAdminService systemAdminService;

    @Autowired
    RegistrationService registrationService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('MEDICAL_ADMIN')")

    public ResponseEntity<List<User>> getAllSystemAdmins(HttpServletRequest request) {
        List<User> admins = systemAdminService.getAllSystemAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // @PreAuthorize("hasRole('MEDICAL_ADMIN')")

    public ResponseEntity<User> getSystemAdmin(@PathVariable String id, HttpServletRequest request) {
        User admin = systemAdminService.getSystemAdmin(id);
        if (admin.equals(null))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    //  @PreAuthorize("hasRole('MEDICAL_ADMIN')")

    public ResponseEntity<User> addSystemAdmin(@RequestBody User admin, HttpServletRequest request) {
        // TO JE BILO -- User a = service.saveOrUpdateSystemAdmin(admin);
        boolean successfullyRegistered = registrationService.registerSystemAdmin(admin, getSiteURL(request));
        if (!successfullyRegistered) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    //  @PreAuthorize("hasRole('MEDICAL_ADMIN')")

    public void deleteUser(@PathVariable String id) {
        systemAdminService.delete(id);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}
