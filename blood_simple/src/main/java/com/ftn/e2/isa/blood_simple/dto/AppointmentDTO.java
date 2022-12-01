package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Timestamp startTime;
    private int duration;
    private Long medicalCenterId;
    private List<User> medicalStaff;

//    public AppointmentDTO(Appointment appointment) {
//        this.id = appointment.getId();
//        this.startTime = appointment.getStartTime();
//        this.duration = appointment.getDuration();
////        this.reserved = appointment.isReserved();
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public long getMedicalCenterId() {
        return medicalCenterId;
    }

    public List<User> getMedicalStaff() {
        return medicalStaff;
    }

}
