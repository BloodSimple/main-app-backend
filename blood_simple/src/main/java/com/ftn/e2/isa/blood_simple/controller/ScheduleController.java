package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleDTO;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.dto.BloodStoreDTO;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "api/centers")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @GetMapping(value = "/{id}/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('MEDICAL_ADMIN', 'SYSTEM_ADMIN')")
    public ResponseEntity<List<Appointment>> getMedicalCenters(@PathVariable Long id, HttpServletRequest request) {
        List<Appointment> list = scheduleService.getAppointmentsByCenter(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/defineAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<Object> createAppointment(@RequestBody AppointmentDTO newAppointmentDTO) {
        Appointment appointment = scheduleService.saveAppointment(newAppointmentDTO);
        if (appointment != null)
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/freeAppointments", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('MEDICAL_ADMIN', 'SYSTEM_ADMIN', USER)")
    public ResponseEntity<List<MedicalCenter>> getMedicalCenterWithAppointments(@RequestParam("startTime")
                                                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                                        LocalDateTime startTime) {
        List<MedicalCenter> medicalCenters = scheduleService.getMedicalCenterWithAppointments(startTime);
        return new ResponseEntity<>(medicalCenters, HttpStatus.OK);
    }

	@PostMapping(value = "/createfreeappointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
	public ResponseEntity<Object> createFreeAppointment(@RequestBody AppointmentDTO newAppointmentDTO){
		System.out.println("Usao u kreiranje app");
		System.out.println("Usao u kreiranje app");
		System.out.println("Usao u kreiranje app");
		System.out.println("Usao u kreiranje app");
		System.out.println("Usao u kreiranje app");
		Appointment appointment = scheduleService.saveAppointment(newAppointmentDTO);
		if (appointment != null)
			return new ResponseEntity<>(appointment, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value="/freeappointments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
	public ResponseEntity<Object> getMedicalCenterFreeAppointments(@PathVariable Long id){
		List<Appointment> list = scheduleService.getCenterFreeAppointments(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

    @PostMapping(value = "/scheduleAppointment/{medicalCenterId}/{startTime}/{personalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AppointmentScheduleDTO> scheduleAppointment(@PathVariable Long medicalCenterId,
                                                                      @PathVariable("startTime")
                                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                              LocalDateTime startTime,
                                                                      @PathVariable String personalId) {
        AppointmentScheduleDTO appointmentScheduleDTO = scheduleService.scheduleAppointment(medicalCenterId, startTime, personalId);
        return new ResponseEntity<>(appointmentScheduleDTO, HttpStatus.OK);

    }

    @PostMapping(value = "/cancelAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AppointmentScheduleDTO> cancelAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentScheduleDTO appointmentScheduleDTO = scheduleService.cancelAppointment(appointmentDTO);
        return new ResponseEntity<>(appointmentScheduleDTO, HttpStatus.OK);

    }

    @GetMapping(value = "myAppointments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable String id) {
        List<Appointment> list = scheduleService.getAppointmentsByUser(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
