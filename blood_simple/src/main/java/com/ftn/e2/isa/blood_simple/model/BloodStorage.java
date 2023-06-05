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

    @Column(name = "storage_ap", nullable = false)
    private double storedAP;
    @Column(name = "storage_an", nullable = false)
    private double storedAN;

    @Column(name = "storage_bp", nullable = false)
    private double storedBP;
    @Column(name = "storage_bn", nullable = false)
    private double storedBN;

    @Column(name = "storage_abp", nullable = false)
    private double storedABP;
    @Column(name = "storage_abn", nullable = false)
    private double storedABN;

    @Column(name = "storage_op", nullable = false)
    private double storedOP;
    @Column(name = "storage_on", nullable = false)
    private double storedON;

    public Long getCenterId() {
        return id;
    }
}
