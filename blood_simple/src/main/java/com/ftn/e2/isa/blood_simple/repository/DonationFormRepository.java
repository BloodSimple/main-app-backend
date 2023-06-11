package com.ftn.e2.isa.blood_simple.repository;

import com.ftn.e2.isa.blood_simple.model.DonationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationFormRepository extends JpaRepository<DonationForm, Long> {

    List<DonationForm> findByUserId(Long userId);
}
