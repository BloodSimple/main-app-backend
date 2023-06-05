package com.ftn.e2.isa.blood_simple.dto;

import javax.persistence.Column;

public class BloodStoreDTO {


    public double storedAP;
    public double storedAN;
    public double storedBP;
    public double storedBN;
    public double storedABP;
    public double storedABN;
    public double storedOP;
    public double storedON;

    public BloodStoreDTO() {
    }

    public BloodStoreDTO(double storedAP,double storedAN, double storedBP, double storedBN,
                         double storedABP,double storedABN, double storedOP,double storedON) {
        this.storedAP = storedAP;
        this.storedAN = storedAN;
        this.storedBP = storedBP;
        this.storedBN = storedBN;
        this.storedABP = storedABP;
        this.storedABN = storedABN;
        this.storedOP = storedOP;
        this.storedON = storedON;
    }
}
