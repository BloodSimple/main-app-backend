package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "BLOOD_DONATIONS")
public class BloodDonation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donation_id")
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column(name = "donation_blood_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private BloodTypeENUM bloodType;
	
	@ManyToOne
	private Appointment appointment;
}
