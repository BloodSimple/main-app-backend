package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.GenderENUM;
import com.ftn.e2.isa.blood_simple.model.RoleENUM;
import com.ftn.e2.isa.blood_simple.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private GenderENUM gender;
    private Address address;
//    private Long addressId;
//    private String addressStreet;
//    private String addressNumber;
//    private String addressCity;
//    private String addressCountry;


    private String phoneNumber;
    private String job;
    private String bio;
    private RoleENUM role;

    public UserDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.gender = user.getGender();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.job = user.getJob();
        this.bio = user.getBio();
        this.role = user.getRole();
    }
}
