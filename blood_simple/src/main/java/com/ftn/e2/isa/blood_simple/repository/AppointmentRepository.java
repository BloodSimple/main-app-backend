package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select A from Appointment A where A.medicalCenter.id = ?1 ")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "0")})
    List<Appointment> getAppointmentsForCenter(Long id);
}
