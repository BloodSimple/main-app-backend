package com.ftn.e2.isa.blood_simple.dto;

public class MedicalStuffSelection {

    public String id;
    public String name;
    public String surname;
    public boolean isSelected;

    public MedicalStuffSelection(String id, String name, String surname, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isSelected = isSelected;
    }

}
