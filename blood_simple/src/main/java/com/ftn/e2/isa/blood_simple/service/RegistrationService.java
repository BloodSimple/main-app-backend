package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.RoleENUM;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
/*
    public boolean registerUser(Map<String, String> map, String siteURL){
        boolean successfullyRegistered = true;
        RoleENUM userType = RoleENUM.valueOf(map.get("role"));
        switch (userType) {
            case USER:
                successfullyRegistered = registerRegularUser( UserDTO.MapToUser(map), siteURL);
                break;
            case MEDICAL_ADMIN:
                successfullyRegistered = registerMedicalAdmin(UserDTO.MapToUser(map), siteURL);
                break;
            case SYSTEM_ADMIN:
                successfullyRegistered = registerSystemAdmin(UserDTO.MapToUser(map), siteURL);
                break;

        }
        return successfullyRegistered;
    }
*/


    public boolean registerRegularUser(Map<String, String> map, String siteURL) {
        User user = (User) UserDTO.MapToUser(map);
        boolean successfullyRegistered = true;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date date = new Date();
        // TODO: setLastPasswordResetDate - Not necessary to set the date - this method can be deleted
        // userDTO.setLastPasswordResetDate(new Timestamp(date.getTime()));
        if(!checkIfEmailExists(user.getEmail())){
            user.setRole(RoleENUM.USER);
            // TODO: setNumberOfLogIns - Not necessary to set the date - this method can be deleted
            //userDTO.setNumberOfLogIns(0);
            // TODO: setVerificationCode - For the next checkpoint
            // setVerificationCode(RandomString.make(64), user);
            try {
                userRepository.saveAndFlush(user);
                // TODO: mailService - For the next checkpoint
                //mailService.sendVerificationEmail(user, siteURL);
            } catch (Exception e) {
                successfullyRegistered = false;
            }
        }
        return successfullyRegistered;
    }

    public boolean registerMedicalAdmin(User user, String siteURL) {
        boolean successfullyRegistered = true;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date date = new Date();
        if(!checkIfEmailExists(user.getEmail())){
            user.setRole(RoleENUM.MEDICAL_ADMIN);
            try {
                userRepository.saveAndFlush(user);
            } catch (Exception e) {
                successfullyRegistered = false;
            }
        }
        return successfullyRegistered;
    }

    public boolean registerSystemAdmin(User user, String siteURL) {
        boolean successfullyRegistered = true;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date date = new Date();
        if(!checkIfEmailExists(user.getEmail())){
            user.setRole(RoleENUM.SYSTEM_ADMIN);
            try {
                userRepository.saveAndFlush(user);
            } catch (Exception e) {
                successfullyRegistered = false;
            }
        }
        return successfullyRegistered;
    }

    private boolean checkIfEmailExists(String email){
        return userRepository.findByEmail(email) != null;
    }

}
