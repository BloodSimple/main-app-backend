package com.ftn.e2.isa.blood_simple.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.e2.isa.blood_simple.model.Appointment;



public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	@Query("select A from Appointment A where A.medicalCenter.id = ?1 ")
	public List<Appointment> getAppointmentsForCenter(Long id);
	
	@Query("select A from Appointment A where A.user.personalId = ?1 and A.startTime = ?2")
	public Appointment findAppointmentByData(String id, java.util.Date date);
}
