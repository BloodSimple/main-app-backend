package com.ftn.e2.isa.blood_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.e2.isa.blood_simple.model.AppointmentReport;
import com.ftn.e2.isa.blood_simple.model.BloodStorage;
import com.ftn.e2.isa.blood_simple.model.EquipmentStorage;

public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Long>{

	@Query("select ES from EquipmentStorage ES where ES.medicalCenter.id = ?1")
	public EquipmentStorage getEquipmentStorageFromCenter(Long id);
	
	@Query("select BS from BloodStorage BS where BS.medicalCenter.id = ?1")
	public BloodStorage getBloodStorageFromCenter(Long id);
}
