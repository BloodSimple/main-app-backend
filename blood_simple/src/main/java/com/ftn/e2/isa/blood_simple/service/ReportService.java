package com.ftn.e2.isa.blood_simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.model.AppointmentReport;
import com.ftn.e2.isa.blood_simple.repository.AppointmentReportRepository;

@Service
public class ReportService {
	
	@Autowired
	AppointmentReportRepository reportRepo;
	
	public AppointmentReport getReportById(Long id) {
		return reportRepo.findById(id).orElse(null);
	}

}
