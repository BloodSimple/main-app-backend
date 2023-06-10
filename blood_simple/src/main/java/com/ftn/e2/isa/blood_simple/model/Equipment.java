package com.ftn.e2.isa.blood_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "EQUIPMENT_STORAGES")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long id;

    @OneToOne
    private MedicalCenter medicalCenter;


    @Column(name = "sbag150")
    private int sbag150;

    @Column(name = "sbag400")
    private int sbag400;

    @Column(name = "sbag600")
    private int sbag600;

    @Column(name = "dbag150")
    private int dbag150;

    @Column(name = "dbag400")
    private int dbag400;

    @Column(name = "dbag600")
    private int dbag600;

    @Column(name = "tbag150")
    private int tbag150;

    @Column(name = "tbag400")
    private int tbag400;

    @Column(name = "tbag600")
    private int tbag600;

    @Column(name = "blood_lancet")
    private int bloodLancet;
}
