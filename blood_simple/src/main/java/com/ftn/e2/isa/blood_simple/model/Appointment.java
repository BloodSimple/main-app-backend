package com.ftn.e2.isa.blood_simple.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
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
	private Timestamp startTime;
	
	@Column(name = "appointment_duration", nullable = false)
	private int duration;

	@Column(name = "reserved", nullable = false)
	private boolean reserved;
	
	@OneToOne
	private User user; 
	
    @Enumerated(EnumType.STRING)
	private BloodTypeENUM bloodType;
	
	private double amountOfBlood;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<User> medicalStaff;
	
	@ManyToOne 	// bidirekciono
	private MedicalCenter medicalCenter;

//	public Appointment(AppointmentDTO appointmentDTO) {
//		this.id = appointmentDTO.getId();
//		this.startTime = appointmentDTO.getStartTime();
//		this.duration = appointmentDTO.getDuration();
////		this.reserved = appointmentDTO.isReserved();
//	}

	public boolean isReserved() {
		return reserved;
	}
}
