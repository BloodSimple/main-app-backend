package com.ftn.e2.isa.blood_simple.dto;

import javax.persistence.Column;

public class BloodStoreDTO {


    public double storedA;
    public double storedB;
    public double storedAB;
    public double storedO;

    public BloodStoreDTO() {
    }

    public BloodStoreDTO(double storedA, double storedB, double storedAB, double storedO) {
        this.storedA = storedA;
        this.storedB = storedB;
        this.storedAB = storedAB;
        this.storedO = storedO;
    }
}
