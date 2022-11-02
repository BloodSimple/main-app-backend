package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name="USERS")
public class User {
	@Id 	//jmbg
	@Column(name = "user_id")
	private String id;

	@Column(name = "user_jmbg", nullable = false, unique = true)
	private String jmbg;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email; 
	
	@Column(name = "user_password", nullable = false)
	private String password; 
	
	@Column(name = "user_name", nullable = false)
	private String name; 
	
	@Column(name = "user_surname", nullable = false)
	private String surname; 
	
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private GenderENUM gender; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_address_id")
	private Address address; 
	
	@Column(name = "user_phone")
	private String phoneNumber;
	
	@Column(name = "user_job")
	private String job; 
	
	@Column(name = "user_bio")
	private String bio;
	
	@Column(name = "user_role", nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleENUM role; 
	
	
}
