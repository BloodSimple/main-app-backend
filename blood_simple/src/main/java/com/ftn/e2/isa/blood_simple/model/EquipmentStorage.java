package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EQUIPMENT_STORAGES")
public class EquipmentStorage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="storage_id")
	private Long id;
	
	@OneToOne	
	private MedicalCenter medicalCenter;  
	
	@Column(name = "blood_bag", nullable = false)
	private int bloodBag;
	
	@Column(name = "needle", nullable = false)
	private int needle;
	
	@Column(name = "syringe", nullable = false)
	private int syringe;
}
