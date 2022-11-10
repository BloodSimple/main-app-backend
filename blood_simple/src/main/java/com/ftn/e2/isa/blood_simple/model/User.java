package com.ftn.e2.isa.blood_simple.model;

import javax.persistence.*;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Data		// @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name="USERS")
public class User implements UserDetails {

	@Id
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue=6, allocationSize = 1)
	@Column(name = "user_id")
	private Long id;

	@Column(name= "user_personal_identification_number", nullable = false, unique = true)
	private String personalIdentificationNumber;

	@Column(name = "user_email", nullable = false, unique = true)
	private String email;

	@Column(name = "user_password", nullable = false)
	private String password;

	@Column(name = "user_name", nullable = false)
	private String name;

	@Column(name = "user_surname", nullable = false)
	private String surname;

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

	@Column(name = "user_type") // gender
	@Enumerated(EnumType.STRING)
	private GenderENUM gender;

	// UserDetails interface methods:

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; // TODO: this.authorities;
	}

	@Override
	public String getUsername() {
		return this.email; //our username is the email
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true; // TODO: isActivated field is necessary
	}

//	public User(UserDTO dto){
//		this.id = dto.getId();
//		this.email = dto.getEmail();
//		this.password = dto.getPassword();
//		this.name = dto.getName();
//		this.surname = dto.getSurname();
//		this.gender = dto.getGender();
//		this.address.getStreet() = dto.getAddressStreet();
//		this.phoneNumber = dto.getPhoneNumber();
//		this.job = dto.getJob();
//		this.bio = dto.getBio();
//		this.role = dto.getRole();
//	}

}
