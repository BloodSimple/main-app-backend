package com.ftn.e2.isa.blood_simple.model;

import com.ftn.e2.isa.blood_simple.dto.CreateReportDTO;
import com.ftn.e2.isa.blood_simple.dto.MedicalCenterDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data        // @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "REPORT_STORAGES")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @OneToOne
    private Appointment appointment;

    @Column(name = "blood_amount", nullable = false)
    private double bloodAmount;

    @Column(name = "doctor_note")
    private String doctorNote;

    @Column(name = "blood_type")
    private String bloodType;

    @Column(name = "puncture_site")
    private String puncture_site;


    @Column(name = "coppsulf")
    private String coppsulf;

    @Column(name = "coppsulf_normal")
    private String coppsulfNormal;

    @Column(name = "coppsulf_low")
    private String coppsulfLow;

    @Column(name = "hemoometer")
    private String hemoometer;

    @Column(name = "readValue")
    private String readValue;

    @Column(name = "lungs")
    private String lungs;

    @Column(name = "heart")
    private String heart;

    @Column(name = "ta")
    private String ta;

    @Column(name = "tt")
    private String tt;

    @Column(name = "tv")
    private String tv;

    @Column(name = "refusal_reason")
    private String refusalReason;


    public Report (CreateReportDTO dto, Appointment app) {

        this.appointment = app;

        this.bloodAmount = dto.bloodAmount;

        this.doctorNote = dto.doctorNote;

        this.bloodType = dto.bloodType;

        this.puncture_site = dto.puncture_site;

        this.coppsulf = dto.coppsulf;

        this.coppsulfNormal = dto.coppsulfNormal;

        this.coppsulfLow = dto.coppsulfLow;

        this.hemoometer = dto.hemoometer;

        this.readValue = dto.readValue;

        this.lungs = dto.lungs;

        this.heart = dto.heart;

        this.ta = dto.ta;

        this.tt = dto.tt;

        this.tv = dto.tv;

        this.refusalReason = dto.refusalReason;

    }

}
