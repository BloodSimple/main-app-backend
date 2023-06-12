package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.*;
import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // TODO: Change the function name to 'getUserByPersonalId' and the @PathVariable name to 'personalId'
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        UserDTO userDTO = userService.getUserByPersonalId(id);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "mail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> getUserMail(@PathVariable String mail) {
        UserDTO userDTO = userService.getUserByMail(mail);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) {
        boolean status = userService.updateUser(userDTO);
        if (status) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/updatepassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MEDICAL_ADMIN', 'SYSTEM_ADMIN')")
    public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordDTO passwordDto) {
        boolean status = userService.updatePassword(passwordDto);
        if (status) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Change the function name to 'deleteUserByPersonalId' and the @PathVariable name to 'personalId'
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        boolean status = userService.deleteUserByPersonalId(id);
        if (status)
            return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
        else
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);

    }


    @GetMapping(value = "/search_users_with_donation", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public  ResponseEntity<Object> searchUsersWithBloodDonation(@PathVariable String id)
    {
        List<UserDTO> foundUsersDto = userService.getUsersByWithBloodDonations(Long.getLong(id));

        return new ResponseEntity<>( foundUsersDto, HttpStatus.OK);
    }

    @GetMapping(value="/donated-blood/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> getUsersWhoDonatedBlood(@PathVariable Long id){
//        List<BloodStoreDTO> dto = medicalCenterService.getBloodStoreForCenter(id);
//        if(dto == null)
//        {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
        //call user service
        List<UserDonatedReportDTO> list = userService.getUsersWhoDonatedBlood(id);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value="/history/{userId}/{mcId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> getUserAppointmentHistory(@PathVariable Long userId, @PathVariable Long mcId){
        List<AppointReportDTO> list = userService.getUserAppointmentHistory(userId, mcId);

        //poslati objekat sa reportom
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value="/appointments-taken/{userId}/{mcId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> getUserTakenAppointments(@PathVariable Long userId, @PathVariable Long mcId){
        List<Appointment> list = userService.getUserTakenAppointments(userId, mcId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(value="/isFirstLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> isFirstMedicalAdminLogin(@RequestBody String mail){
//        List<Appointment> list = userService.getUserTakenAppointments(userId, mcId);
        boolean login = userService.isFirstMedicalAdminLogin(mail);
        return new ResponseEntity<>(login, HttpStatus.OK);
//        return null;
    }

}
