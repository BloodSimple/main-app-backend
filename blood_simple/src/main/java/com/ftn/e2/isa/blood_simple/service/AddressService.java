package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.AddressDTO;
import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address != null) {
            return new AddressDTO(address);
        } else {
            return null;
        }
    }

}
