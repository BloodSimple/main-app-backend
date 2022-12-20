package com.ftn.e2.isa.blood_simple.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleDTO;
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
	public AppointmentScheduleDTO scheduleAppointment(Long medicalCenterId, LocalDateTime startTime, String personalId){
		List<Appointment> appointments = getAppointmentsByCenter(medicalCenterId);
		AppointmentScheduleDTO appointmentSchedule = new AppointmentScheduleDTO();
		for(Appointment appointment: appointments) {
			if (appointment.getStartTime().equals(startTime) && appointment.isReserved() == false) {
				User user = userRepository.findByPersonalId(personalId);
					if(user.getBloodDonation() != null){
						if(LocalDateTime.now().isBefore(user.getBloodDonation().plusMonths(6))) {
							appointmentSchedule.setResponse("Six months haven't passed since your last blood donation.");
							return appointmentSchedule;
						}
					}
					if(user.getQuiestionnaire() == null){
						appointmentSchedule.setResponse("You should take questionnaire before blood donation.");
						return appointmentSchedule;
					}else if(user.getQuiestionnaire().plusDays(1).isBefore(LocalDateTime.now())){
						appointmentSchedule.setResponse("You should take questionnaire again...");
						return appointmentSchedule;
					}

				appointment.setReserved(true);
				appointment.setUser(user);
				user.setBloodDonation(startTime);
				appointmentRepo.save(appointment);
				try {
					mailService.sendSuccessfulReservationEmail(appointment.getUser(), appointment);
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		appointmentSchedule.setResponse("Successfully reserved appointment");
		return appointmentSchedule;
	}

	public List<Appointment> getAppointmentsByUser(String personalId){
		List<Appointment> allAppointments = appointmentRepo.findAll();
		List<Appointment> usersAppointments = new ArrayList<>();
		for(Appointment appointment: allAppointments){
			if(appointment.getUser().getPersonalId().equals(personalId)){
				usersAppointments.add(appointment);
//				return usersAppointments;
			}
		}
		return usersAppointments;
	}
}
