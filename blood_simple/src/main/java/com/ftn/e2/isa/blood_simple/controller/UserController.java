package com.ftn.e2.isa.blood_simple.controller;
import com.ftn.e2.isa.blood_simple.dto.UpdatePasswordDTO;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> list = userService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // TODO: Change the function name to 'getUserByPersonalId' and the @PathVariable name to 'personalId'
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   // @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Object> getUserById(@PathVariable String id){
        UserDTO userDTO = userService.getUserByPersonalId(id);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }




    @PutMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO){
       boolean status = userService.updateUser(userDTO);
       if(status){
           return new ResponseEntity<>(userDTO, HttpStatus.OK);
       }else {
           return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping(value="/updatepassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordDTO passwordDto){
        boolean status = userService.updatePassword(passwordDto);
        if(status){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Change the function name to 'deleteUserByPersonalId' and the @PathVariable name to 'personalId'
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        boolean status = userService.deleteUserByPersonalId(id);
        if(status)
            return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
        else
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);

    }


}
