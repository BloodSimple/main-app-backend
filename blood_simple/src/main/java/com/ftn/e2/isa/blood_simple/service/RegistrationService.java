package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.RoleENUM;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.MedicalCenterRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
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
    @Autowired
    private MedicalCenterRepository medicalCenterRepo;
    @Autowired
    private MailService mailService;

    public boolean registerRegularUser(Map<String, String> map, String siteURL) {
        boolean successfullyRegistered = true;
        User user = UserDTO.MapToUser(map);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (checkIfEmailExists(user.getEmail())) {
            user.setRole(RoleENUM.USER); // Maybe this should be done in the UserDTO.MapToUser() method
            setVerificationCode(RandomString.make(64), user);
            try {
                mailService.sendVerificationEmail(user, siteURL);
                /* TODO: Add Authority to the user
                user.setAuthorities(...);       */
                userRepository.save(user);
            } catch (Exception e) {
                successfullyRegistered = false;
            }
        } else {
            successfullyRegistered = false;
        }
        return successfullyRegistered;
    }

    public boolean verifyRegularUserAccount(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        boolean retVal = user != null && !user.isActivated() && activateRegularUserAccount(user);
        if (retVal) {
            // TODO: Add New Penalty for the activated regular user.
            // penalService.addNewPenal(user);
        }
        return retVal;
    }

    private boolean activateRegularUserAccount(User user) {
        user.setActivated(true);
        setVerificationCode("", user);
        userRepository.save(user);
        return true;
    }

    private void setVerificationCode(String code, User user) {
        user.setVerificationCode(code);
    }

    public boolean registerMedicalAdmin(User user, String siteURL) {
        boolean successfullyRegistered = true;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Date date = new Date();
        if (checkIfEmailExists(user.getEmail())) {
            user.setRole(RoleENUM.MEDICAL_ADMIN);
            try {
                if (user.equals(null) || user.getRole() != RoleENUM.MEDICAL_ADMIN)
                    successfullyRegistered = false;
                for (MedicalCenter mc : medicalCenterRepo.findAll()) {
                    if (!mc.getMedicalAdmins().isEmpty()) {
                        for (User a : mc.getMedicalAdmins()) {
                            if (a.getPersonalId().equals(user.getPersonalId()))
                                successfullyRegistered = false;
                        }
                    }
                }
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
        if (checkIfEmailExists(user.getEmail())) {
            user.setRole(RoleENUM.SYSTEM_ADMIN);
            try {
                userRepository.saveAndFlush(user);
            } catch (Exception e) {
                successfullyRegistered = false;
            }
        }
        return successfullyRegistered;
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.findByEmail(email) == null;
    }

}
