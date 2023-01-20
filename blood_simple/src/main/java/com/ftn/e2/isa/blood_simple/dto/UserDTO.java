package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String personalId;
    private String email;
    private String password;
    private String name;
    private String surname;
    private GenderENUM gender;
//    private Address address;

    private Long addressId;
    private String addressStreet;
    private String addressNumber;
    private String addressCity;
    private String addressCountry;
//    private double addressX;
//    private double addressY;


    private DonationForm donationForm;

    private String phoneNumber;
    private String job;
    private String bio;
    private RoleENUM role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.personalId = user.getPersonalId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.gender = user.getGender();
        this.addressId = user.getAddress().getId();
        this.addressStreet = user.getAddress().getStreet();
        this.addressNumber = user.getAddress().getNumber();
        this.addressCity = user.getAddress().getCity();
        this.addressCountry = user.getAddress().getCountry();
        this.phoneNumber = user.getPhoneNumber();
        this.job = user.getJob();
        this.bio = user.getBio();
        this.role = user.getRole();
        if(user.getDonationForm() != null)
            this.donationForm = user.getDonationForm();


    }

    public static User MapToUser(Map<String, String> map) {
        return new User(
                map.get("personalId"),
                map.get("email"),
                map.get("password"),
                map.get("firstName"),
                map.get("lastName"),
                new Address(
                        map.get("addressStreet"),
                        map.get("addressNumber"),
                        map.get("addressCity"),
                        map.get("addressCountry")
                ),
                map.get("phoneNumber"),
                map.get("job"),
                map.get("bio"),
                RoleENUM.valueOf(map.get("role")),
                GenderENUM.valueOf(map.get("gender"))
                );
    }

}
