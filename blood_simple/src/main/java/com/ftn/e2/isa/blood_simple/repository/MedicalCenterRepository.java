package com.ftn.e2.isa.blood_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;



public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long>{

	MedicalCenter getByName(String id);
	MedicalCenter findOneById(Long id);
}
