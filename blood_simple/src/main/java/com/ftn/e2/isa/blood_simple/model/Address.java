package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Table(name="ADDRESSES")
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	
	@Column(nullable =  false)
	private String street;

	@Column(nullable =  false)
	private String number; 	// jer moze biti 1A, 32B itd...
	
	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String country;

	@Column(nullable = true)
	private double longitude; // x-coordinate

	@Column(nullable = true)
	private double latitude; // y-coordinate
	


}
