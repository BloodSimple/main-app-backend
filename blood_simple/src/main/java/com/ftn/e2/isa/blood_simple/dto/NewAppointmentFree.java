package com.ftn.e2.isa.blood_simple.dto;

import java.time.LocalDateTime;
import java.util.List;

public class NewAppointmentFree {
    public LocalDateTime startTime;
    public int duration;
    public Long medicalCenterId;
    public List<MedicalStuffSelection> medicalStaff;

    public NewAppointmentFree(LocalDateTime startTime, int duration, Long medicalCenterId, List<MedicalStuffSelection> medicalStaff) {
        this.startTime = startTime;
        this.duration = duration;
        this.medicalCenterId = medicalCenterId;
        this.medicalStaff = medicalStaff;
    }
}

