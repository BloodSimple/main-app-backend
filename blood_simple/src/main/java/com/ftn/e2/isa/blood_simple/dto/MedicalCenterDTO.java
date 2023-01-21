package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalCenterDTO {
    private Long id;
    private Address address;
    private String name;
    private String description;
    private Set<User> admins;
    private Set<User> medicalStaff;        //stavi na dto posle merge

    private double grade;

    public MedicalCenterDTO(MedicalCenter model) {
        this.id = model.getId();
        this.name = model.getName();
        this.address = model.getAddress();
        this.description = model.getDescription();
        this.admins = model.getMedicalAdmins();
        this.medicalStaff = model.getMedicalStaff(); // izmeni za merge
        this.grade = model.getGrade();
    }

    public MedicalCenterDTO model2Dto(MedicalCenter model) {
        MedicalCenterDTO dto = new MedicalCenterDTO(model);
        return dto;
    }

    public MedicalCenter dto2Model(MedicalCenterDTO dto) {
        MedicalCenter model = new MedicalCenter(dto);
        return model;
    }
}
