package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private LocalDateTime startTime;
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

    public LocalDateTime getStartTime() {
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
