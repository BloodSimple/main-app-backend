package com.ftn.e2.isa.blood_simple.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
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
	@Autowired
	UserRepository userRepository;
	
	public List<Appointment>getAppointmentsByCenter(Long id){
		return appointmentRepo.getAppointmentsForCenter(id);
	}

	public Appointment saveAppointment(AppointmentDTO appointmentDTO){
		Appointment appointment = new Appointment();
		appointment.setStartTime(appointmentDTO.getStartTime());
		appointment.setDuration(appointmentDTO.getDuration());
		appointment.setReserved(false);
		MedicalCenter medicalCenter = centerRepo.findOneById(Long.valueOf(appointmentDTO.getMedicalCenterId()));
		System.out.println(appointmentDTO.getMedicalCenterId());
		appointment.setMedicalCenter(medicalCenter);
		List<User> staff = new ArrayList<User>();
		for(User user: appointmentDTO.getMedicalStaff()) {
			User medStaff = userRepository.findByPersonalId(user.getPersonalId());
			staff.add(medStaff);
		}
		appointment.setMedicalStaff(staff);
		appointmentRepo.save(appointment);
		return appointment;
	}
}
