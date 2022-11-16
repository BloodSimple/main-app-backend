package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name="ADDRESSES")
@AllArgsConstructor

public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(name = "address_street", nullable =  false)
	private String street;

	@Column(name="address_number")
	private String number;

	@Column(name = "address_city", nullable = false)
	private String city;

	@Column(name = "address_country", nullable = false)
	private String country;

	@Column(name = "address_x", nullable = true)
	private double x;

	@Column(name = "address_y", nullable = true)
	private double y;

	public Address(String street, String number, String city, String country) {
		super();
		this.street = street;
		this.number = number;
		this.city = city;
		this.country = country;
	}


}
