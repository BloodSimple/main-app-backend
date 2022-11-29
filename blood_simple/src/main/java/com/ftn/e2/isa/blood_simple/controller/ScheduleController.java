package com.ftn.e2.isa.blood_simple.controller;

import org.springframework.http.MediaType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
