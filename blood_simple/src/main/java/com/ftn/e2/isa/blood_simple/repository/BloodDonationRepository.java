package com.ftn.e2.isa.blood_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.e2.isa.blood_simple.model.BloodDonation;



public interface BloodDonationRepository extends JpaRepository<BloodDonation, Long>{

}
