package com.ftn.e2.isa.blood_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.e2.isa.blood_simple.model.BloodStorage;
import com.ftn.e2.isa.blood_simple.model.EquipmentStorage;


public interface EquipmentStorageRepository extends JpaRepository<EquipmentStorage, Long>{

}
