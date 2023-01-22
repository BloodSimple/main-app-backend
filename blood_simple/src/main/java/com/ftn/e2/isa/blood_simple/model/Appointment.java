package com.ftn.e2.isa.blood_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data        // @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "APPOINTMENTS")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @Version
    private int version;

    @Column(name = "appointment_start", nullable = false) // datum i vreme
    private LocalDateTime startTime;

    @Column(name = "appointment_duration", nullable = false)
    private Integer duration;

    @Column(name = "reserved", nullable = false)
    private boolean reserved;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private BloodTypeENUM bloodType;

    @Column(name = "amount_of_blood")
    private Double amountOfBlood;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> medicalStaff;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> cancelledUsers;

    @ManyToOne    // bidirekciono
    private MedicalCenter medicalCenter;

//	public Appointment(AppointmentDTO appointmentDTO) {
//		this.id = appointmentDTO.getId();
//		this.startTime = appointmentDTO.getStartTime();
//		this.duration = appointmentDTO.getDuration();
////		this.reserved = appointmentDTO.isReserved();
//	}

    public boolean isReserved() {
        return reserved;
    }
}
