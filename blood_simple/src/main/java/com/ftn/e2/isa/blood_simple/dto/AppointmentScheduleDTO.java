package com.ftn.e2.isa.blood_simple.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentScheduleDTO {
    private Long medicalCenterId;
    private LocalDateTime startTime;
    private String personalId;

}
