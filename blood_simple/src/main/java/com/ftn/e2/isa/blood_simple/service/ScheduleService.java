package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleResponseDTO;
import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.ftn.e2.isa.blood_simple.repository.MedicalCenterRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;


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

    public List<Appointment> getAppointmentsByCenter(Long id) {
        return appointmentRepo.getAppointmentsForCenter(id);
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        System.out.println(appointmentDTO.getStartTime());
        appointment.setStartTime(appointmentDTO.getStartTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment.setReserved(false);
        MedicalCenter medicalCenter = centerRepo.findOneById(appointmentDTO.getMedicalCenterId());
        System.out.println(appointmentDTO.getMedicalCenterId());
        appointment.setMedicalCenter(medicalCenter);
        List<User> staff = new ArrayList<User>();
        for (User user : appointmentDTO.getMedicalStaff()) {
            User medStaff = userRepository.findByPersonalId(user.getPersonalId());
            staff.add(medStaff);
        }
        appointment.setMedicalStaff(staff);
        appointmentRepo.save(appointment);
        return appointment;
    }

    public AppointmentScheduleResponseDTO cancelAppointment(AppointmentDTO appointmentDTO) {
        AppointmentScheduleResponseDTO appointmentScheduleResponseDTO = new AppointmentScheduleResponseDTO();
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentDTO.getId());
        if(appointment.isPresent()){
            appointment.get().setReserved(false);
            appointment.get().getCancelledUsers().add(appointmentDTO.getUser());
            appointment.get().setUser(null);
            appointmentRepo.save(appointment.get());
            appointmentScheduleResponseDTO.setResponse("Successfully cancelled appointment");
            return appointmentScheduleResponseDTO;
        }
        appointmentScheduleResponseDTO.setResponse("Something wrong happened...");
        return appointmentScheduleResponseDTO;
    }

    public List<MedicalCenter> getMedicalCenterWithAppointments(LocalDateTime startTime) {
        List<MedicalCenter> goodMedicalCenters = new ArrayList<>();
        List<Appointment> goodAppointments = new ArrayList<>();
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for (Appointment appointment : allAppointments) {
            if (!appointment.isReserved() && appointment.getStartTime().equals(startTime)) {
                goodAppointments.add(appointment);
                System.out.println(appointment);
            }
        }
        for (Appointment app : goodAppointments) {
            goodMedicalCenters.add(app.getMedicalCenter());
        }

        return goodMedicalCenters;
    }

    @Transactional
    public AppointmentScheduleResponseDTO scheduleAppointment(Long medicalCenterId, LocalDateTime startTime, String personalId) {
        List<Appointment> appointments = getAppointmentsByCenter(medicalCenterId);
        AppointmentScheduleResponseDTO appointmentScheduleResponseDTO = new AppointmentScheduleResponseDTO();
        for (Appointment appointment : appointments) {
            if (appointment.getStartTime().equals(startTime) && !appointment.isReserved()) {
                User user = userRepository.findByPersonalId(personalId);
                if (user.getLastBloodDonation() != null) {
                    if (LocalDateTime.now().isBefore(user.getLastBloodDonation().plusMonths(6))) {
                        appointmentScheduleResponseDTO.setResponse("Six months haven't passed since your last blood donation.");
                        return appointmentScheduleResponseDTO;
                    }
                }
                if (user.getDonationForm() == null) {
                    appointmentScheduleResponseDTO.setResponse("You should take questionnaire before blood donation.");
                    return appointmentScheduleResponseDTO;
                } else if (user.getDonationForm().getDate().plusDays(1).isBefore(LocalDateTime.now())) {
                    appointmentScheduleResponseDTO.setResponse("You should take questionnaire again...");
                    return appointmentScheduleResponseDTO;
                }
                if(appointment.getCancelledUsers().contains(user)){
                    appointmentScheduleResponseDTO.setResponse("You have cancelled this appointment, you can't schedule it again.");
                    return appointmentScheduleResponseDTO;
                }
                appointment.setReserved(true);
                appointment.setUser(user);
               //user.setLastBloodDonation(startTime); TODO: Delete it! We don't put it till the end of the blood donation process
                appointmentRepo.save(appointment);
                try {
                    mailService.sendSuccessfulReservationEmail(appointment.getUser(), appointment);
                } catch (MessagingException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                appointmentScheduleResponseDTO.setResponse("Successfully reserved appointment");
                return appointmentScheduleResponseDTO;
            }
        }
        appointmentScheduleResponseDTO.setResponse("There is no defined appointment for this medical center");
        return appointmentScheduleResponseDTO;
    }

    public List<Appointment> getAppointmentsByUser(String personalId) {
        List<Appointment> allAppointments = appointmentRepo.findAll();
        List<Appointment> usersAppointments = new ArrayList<>();
        for (Appointment appointment : allAppointments) {
            if (appointment.getUser() != null) {
                if (appointment.getUser().getPersonalId().equals(personalId)) {
                    usersAppointments.add(appointment);
                }
            }
        }
        return usersAppointments;
    }


	public List<Appointment> getCenterFreeAppointments(Long id){

		List<Appointment> allAppointments = getAppointmentsByCenter(id);
		List<Appointment> freeAppointments = new ArrayList<>();
		for(Appointment appointment: allAppointments){
			if(appointment.getUser() == null){
				freeAppointments.add(appointment);
			}
		}
		return freeAppointments;
	}

}
