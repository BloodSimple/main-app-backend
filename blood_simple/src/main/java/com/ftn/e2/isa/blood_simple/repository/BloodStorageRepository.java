package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.BloodStorage;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodStorageRepository extends JpaRepository<BloodStorage, Long> {

    Optional<BloodStorage> findByMedicalCenter(MedicalCenter medicalCenter);

}
