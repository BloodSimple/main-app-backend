package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.Equipment;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Optional<Equipment> findByMedicalCenter(MedicalCenter medicalCenter);

}
