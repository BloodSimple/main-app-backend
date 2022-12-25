package com.ftn.e2.isa.blood_simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.e2.isa.blood_simple.service.ReportService;

@RestController
@RequestMapping(value="api/report")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	
}
