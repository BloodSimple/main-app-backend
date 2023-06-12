package com.ftn.e2.isa.blood_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
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
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private RoleENUM role;
    @Column(name = "user_type") // gender
    @Enumerated(EnumType.STRING)
    private GenderENUM gender;
    @Column(name = "user_blood_type") // gender
    @Enumerated(EnumType.STRING)
    private BloodTypeENUM bloodType;

    // For Blood Donations
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "donationForm", referencedColumnName = "donation_form_id")
    private DonationForm donationForm;
    @Column(name = "last_blood_donation")
    private LocalDateTime lastBloodDonation;

    // For Authority - Which roles have authority for some actions?
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "authority_id"))
    private List<Role> authorities;

    // For Registration and Verification
    @Column(name = "first_login", columnDefinition = "boolean default false") //
    private boolean first_login;
    @Column(name = "isActivated", nullable = false)
    protected boolean isActivated = false;
    @Column(name = "verificationCode", nullable = false)
    protected String verificationCode;

    @Column(name = "negative_points")
    private Long negativePoints;

    public User(String personalId, String email, String password, String name,
                String surname, Address address, String phoneNumber,
                String job, String bio, RoleENUM role, GenderENUM gender) {
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
        return isActivated;
    }
}
