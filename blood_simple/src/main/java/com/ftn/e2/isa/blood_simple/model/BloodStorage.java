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
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "BLOOD_STORAGES")
public class BloodStorage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "storage_id")
	private Long id;
	
	@OneToOne	
	private MedicalCenter medicalCenter;  
	
	@Column(name = "storage_a", nullable = false)
	private double storedA;
	
	@Column(name = "storage_b", nullable = false)
	private double storedB;
	
	@Column(name = "storage_ab", nullable = false)
	private double storedAB;
	
	@Column(name = "storage_o", nullable = false)
	private double storedO;

	public Long getCenterId()
	{
		return medicalCenter.getId();
	}

	public double getStoredA() {
		return storedA;
	}

	public void setStoredA(double storedA) {
		this.storedA = storedA;
	}

	public double getStoredB() {
		return storedB;
	}

	public void setStoredB(double storedB) {
		this.storedB = storedB;
	}

	public double getStoredAB() {
		return storedAB;
	}

	public void setStoredAB(double storedAB) {
		this.storedAB = storedAB;
	}

	public double getStoredO() {
		return storedO;
	}

	public void setStoredO(double storedO) {
		this.storedO = storedO;
	}
}
