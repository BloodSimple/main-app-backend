package com.ftn.e2.isa.blood_simple.model;

import lombok.Data;

@Data
public class ReportRequest {
	private AppointmentReport appointmetReport; 
	private String bloodType;
	private double amountOfBlood;
	private int bags; 
	private int needles; 
	private int syringes;
}
