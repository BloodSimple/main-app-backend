package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("select A from Appointment A where A.medicalCenter.id = ?1 ")
	List<Appointment> getAppointmentsForCenter(Long id);

    List<Appointment> findByStatus(AppointmentStatus status);
}
