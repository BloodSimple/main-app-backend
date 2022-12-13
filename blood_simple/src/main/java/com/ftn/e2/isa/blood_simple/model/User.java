package com.ftn.e2.isa.blood_simple.model;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data        // @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED)


@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 6, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // id in the database {1,2,3,...}

    @Column(name = "user_personal_id", nullable = false, unique = true)
    private String personalId; // JMBG in Serbia

    @Column(name = "user_email", nullable = false, unique = true)
    private String email; // username

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String name; // firstName

    @Column(name = "user_surname", nullable = false)
    private String surname; // lastName

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    // For authentication
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private List<Authority> authorities;

    // UserDetails interface methods - for Authorization and Authentication:

    @Column(name = "questionnaire")
    private LocalDateTime quiestionnaire;

    @Column(name = "blood_donation")
    private LocalDateTime bloodDonation;


    // TODO: Delete this method
    public User(UserDTO dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.gender = dto.getGender();
        this.address = new Address(dto.getAddressStreet(), dto.getAddressNumber(), dto.getAddressCity(), dto.getAddressCountry());
        this.phoneNumber = dto.getPhoneNumber();
        this.job = dto.getJob();
        this.bio = dto.getBio();
        this.role = dto.getRole();
    }

    public User(String personalId, String email, String password, String name, String surname, Address address, String phoneNumber, String job, String bio, RoleENUM role, GenderENUM gender) {
        this.personalId = personalId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.bio = bio;
        this.role = role;
        this.gender = gender;
    }

    public LocalDateTime getQuiestionnaire() {
        return quiestionnaire;
    }

    public void setQuiestionnaire(LocalDateTime quiestionnaire) {
        this.quiestionnaire = quiestionnaire;
    }

    public LocalDateTime getBloodDonation() {
        return bloodDonation;
    }

    public void setBloodDonation(LocalDateTime bloodDonation) {
        this.bloodDonation = bloodDonation;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
        return true; // TODO: isActivated field is necessary - LATER
    }
}
