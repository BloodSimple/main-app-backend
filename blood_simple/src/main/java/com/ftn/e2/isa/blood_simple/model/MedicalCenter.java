package com.ftn.e2.isa.blood_simple.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;

import com.ftn.e2.isa.blood_simple.dto.MedicalCenterDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "MEDICAL_CENTERS")
public class MedicalCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "center_id")
	private Long id;
	
	@Column(name = "center_name", nullable = false, unique=true)
	private String name; 
	
	@OneToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "center_address_id", nullable = false, unique = true) // ne mogu 2 centra biti na istoj adresi
	private Address address;
	
	@Column(name = "center_description", nullable = true)
	private String description;
	
	@OneToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name = "center_admin_user_id")
	private User admin;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<User> medicalStaff;
	
	//lista appointmenta  - da li da bude bidirekciono?
	
	public MedicalCenter(MedicalCenterDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.address = dto.getAddress();
		this.description = dto.getDescription();
		this.admin = dto.getAdmin();
		this.medicalStaff = dto.getMedicalStaff();
	}
	
}
