package com.ftn.e2.isa.blood_simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.e2.isa.blood_simple.model.AppointmentReport;
import com.ftn.e2.isa.blood_simple.model.ReportRequest;
import com.ftn.e2.isa.blood_simple.service.QrService;
import com.ftn.e2.isa.blood_simple.service.ReportService;

@RestController
@RequestMapping(value="api/report")
public class ReportController {

	@Autowired
	private ReportService reportService;
	@Autowired 
	private QrService qrService;
	
	@PutMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> postReport(@RequestBody ReportRequest rr){
		qrService.changeBloodStorage(rr);
		qrService.changeEquipmentStorage(rr);
		AppointmentReport report = reportService.saveOrUpdateAppointmentReport(rr.getAppointmentReport());
		if (report == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	
}
