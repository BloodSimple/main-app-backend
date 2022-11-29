package com.ftn.e2.isa.blood_simple.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.e2.isa.blood_simple.model.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
	Image getByName(String name);
}
