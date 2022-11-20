package com.ftn.e2.isa.blood_simple.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "APPOINTMENTS")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long id;
	
	@Column(name = "appointment_start", nullable = false) // datum i vreme 
	private Date startTime;
	
	@Column(name = "appointment_duration", nullable = false)
	private int duration;
	
	@OneToOne
	private User user; 
	
	private BloodTypeENUM bloodType;
	
	private double amountOfBlood;
	
	@OneToMany
	private List<User> medicalStaff;
	
	@ManyToOne 	// bidirekciono
	private MedicalCenter medicalCenter;
}
