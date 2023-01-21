package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.RoleENUM;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.AddressRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    AddressRepository addRepo;
    @Autowired
    MedicalCenterService medicalService;

    public User getSystemAdmin(String personalId) {
        User admin = userRepo.findByPersonalId(personalId);
        if (admin.equals(null) || !(admin.getRole().equals(RoleENUM.SYSTEM_ADMIN))) {
            return null;
        }
        return admin;
    }

    public List<User> getAllSystemAdmins() {
        return userRepo.getAllSystemAdmins();
    }

    public User saveOrUpdateSystemAdmin(User admin) {
        Address address = medicalService.saveOrUpdateAddress(admin.getAddress());
        if (address != null) {
            admin.setAddress(address);
            return userRepo.save(admin);
        }
        return null;
    }

    public void delete(String personalId) {
        userRepo.deleteByPersonalId(personalId);
    }

}
