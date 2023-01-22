package com.ftn.e2.isa.blood_simple.repository;

import java.sql.Date;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.ftn.e2.isa.blood_simple.model.Appointment;



public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	@Query("select A from Appointment A where A.medicalCenter.id = ?1 ")
	public List<Appointment> getAppointmentsForCenter(Long id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select A from Appointment A where A.user.personalId = ?1 and A.startTime = ?2")
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
	public Appointment findAppointmentByData(String id, java.util.Date date);
}
