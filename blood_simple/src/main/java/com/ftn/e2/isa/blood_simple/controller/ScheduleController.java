package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import org.springframework.http.MediaType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.service.ScheduleService;

@RestController
@RequestMapping(value="api/centers")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@GetMapping(value="/{id}/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getMedicalCenters(@PathVariable Long id, HttpServletRequest request){
		List<Appointment> list = scheduleService.getAppointmentsByCenter(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/defineAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAppointment(@RequestBody AppointmentDTO newAppointmentDTO){
		AppointmentDTO appointmentDTO = scheduleService.saveAppointment(newAppointmentDTO);
		if (appointmentDTO != null)
			return new ResponseEntity<>(appointmentDTO, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}
