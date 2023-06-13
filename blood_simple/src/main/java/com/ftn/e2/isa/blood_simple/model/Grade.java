package com.ftn.e2.isa.blood_simple.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "GRADES")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;

    @Column(name = "center_id")
    private Long centerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "grade")
    private Long grade;


}
