package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.model.Role;
import com.ftn.e2.isa.blood_simple.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public Role findById(Long id) {
        return this.roleRepository.getReferenceById(id);
    }

}
