package com.ftn.e2.isa.blood_simple.dto;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Timestamp startTime;
    private int duration;
    private boolean reserved;

    public AppointmentDTO(Appointment appointment) {
        this.id = appointment.getId();
        this.startTime = appointment.getStartTime();
        this.duration = appointment.getDuration();
        this.reserved = appointment.isReserved();
    }

    public boolean isReserved() {
        return reserved;
    }

}
