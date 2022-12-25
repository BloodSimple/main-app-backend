package com.ftn.e2.isa.blood_simple.model;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="APPOINTMENT_REPORTS")
public class AppointmentReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="report_id")
	private Long id;
	
	@OneToOne
	private Appointment appointment;
	
	@Column(name="number_of_donations")
	private int number_of_donations;
	
	@Enumerated(EnumType.STRING)
	@Column(name="blood_type", nullable = false)
	private BloodTypeENUM bloodType;
	
	@Column(name="amount_of_blood", nullable = false)
	private double amountOfBlood;
	
	@Column(name="copper_sulphate")
	private double copperSulphate;
	
	@Column(name="hemoglobinometer")
	private double hemoglobinometer;
	
	@Column(name="approved", nullable = false)
	private boolean approved; 
	
	@Column(name="denial_reason")
	private String denialReason;
	
	@Column(name="left_hand", nullable = false)
	private boolean leftHand; 
	
	@Column(name="stop_reason")
	private String stopReason;
	
	public AppointmentReport(Appointment a) {
		this.appointment = a;
	}
	
}
