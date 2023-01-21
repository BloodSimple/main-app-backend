package com.ftn.e2.isa.blood_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data        // @getter, @setter i @requiredargsconstructor
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "BLOOD_STORAGES")
public class BloodStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Long id;

    @OneToOne
    private MedicalCenter medicalCenter;

    @Column(name = "storage_a", nullable = false)
    private double storedA;

    @Column(name = "storage_b", nullable = false)
    private double storedB;

    @Column(name = "storage_ab", nullable = false)
    private double storedAB;

    @Column(name = "storage_o", nullable = false)
    private double storedO;

    public Long getCenterId() {
        return id;
    }
}
