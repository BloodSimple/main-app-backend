package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.DonationFormDTO;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.DonationForm;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.DonationFormRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DonationFormService {

    @Autowired
    private DonationFormRepository donationFormRepository;
    @Autowired
    private UserRepository userRepository;

    public boolean saveDonationForm(DonationFormDTO dto, User user, String siteURL) {
        if(user==null){
            return false;
        }
        if(user.getDonationForm()==null){
            user.setDonationForm(new DonationForm(dto));
        }else{
            user.getDonationForm().setDonationForm(dto);
        }
        user.setQuestionnaire(LocalDateTime.now());
        userRepository.saveAndFlush(user);
        return true;
    }

}
