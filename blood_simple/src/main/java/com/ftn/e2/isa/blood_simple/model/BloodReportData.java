package com.ftn.e2.isa.blood_simple.model;

public class BloodReportData {
    public Long medicalCenterId;
    public double bloodAmount;
    public String bloodType;

    public BloodReportData(Long medicalCenterId, double bloodAmount, String bloodType) {
        this.medicalCenterId = medicalCenterId;
        this.bloodAmount = bloodAmount;
        this.bloodType = bloodType;
    }
}
