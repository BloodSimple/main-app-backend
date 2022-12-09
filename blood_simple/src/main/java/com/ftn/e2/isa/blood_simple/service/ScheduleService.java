package com.ftn.e2.isa.blood_simple.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.ftn.e2.isa.blood_simple.repository.MedicalCenterRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;


@Service
public class ScheduleService {
	
	@Autowired
	MedicalCenterRepository centerRepo;
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService mailService;

	public List<Appointment>getAppointmentsByCenter(Long id){
		return appointmentRepo.getAppointmentsForCenter(id);
	}

	public Appointment saveAppointment(AppointmentDTO appointmentDTO){
		Appointment appointment = new Appointment();
		System.out.println(appointmentDTO.getStartTime());
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

	public List<MedicalCenter> getMedicalCenterWithAppointments(LocalDateTime startTime){
		List<MedicalCenter> goodMedicalCenters = new ArrayList<>();
		List<Appointment> goodAppointments = new ArrayList<>();
		List<Appointment> allAppointments = appointmentRepo.findAll();
		for(Appointment appointment: allAppointments){
			if(appointment.isReserved() == false && appointment.getStartTime().equals(startTime) ){
				goodAppointments.add(appointment);
				System.out.println(appointment);
			}
		}
		for(Appointment app: goodAppointments){
			goodMedicalCenters.add(app.getMedicalCenter());
		}

		return goodMedicalCenters;
	}

	@Transactional
	public Appointment scheduleAppointment(Long medicalCenterId, LocalDateTime startTime, String personalId){
		List<Appointment> appointments = getAppointmentsByCenter(medicalCenterId);
		for(Appointment appointment: appointments) {
			if (appointment.getStartTime().equals(startTime) && appointment.isReserved() == false) {
				appointment.setReserved(true);
				appointment.setUser(userRepository.findByPersonalId(personalId));
				appointmentRepo.save(appointment);
				try {
					mailService.sendSuccessfulReservationEmail(appointment.getUser(), appointment);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return appointment;
			}
		}
		return null;
	}
}
