package com.ftn.e2.isa.blood_simple.service;

import java.util.List;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.ftn.e2.isa.blood_simple.repository.MedicalCenterRepository;


@Service
public class ScheduleService {
	
	@Autowired
	MedicalCenterRepository centerRepo;
	@Autowired
	AppointmentRepository appointmentRepo;
	
	public List<Appointment>getAppointmentsByCenter(Long id){
		return appointmentRepo.getAppointmentsForCenter(id);
	}

	public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO){
		Appointment appointment = new Appointment(appointmentDTO);
		appointmentRepo.save(appointment);
		return new AppointmentDTO(appointment);
	}
}
