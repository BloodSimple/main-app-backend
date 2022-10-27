package com.ftn.e2.isa.blood_simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.repository.*;

@SpringBootApplication
@ComponentScan("repository")
public class BloodSimpleApplication implements CommandLineRunner {

	@Autowired
	AddressRepository addressRepo;
	@Autowired
	AppointmentRepository appointmentRepo;
	@Autowired
	BloodDonationRepository donationRepo;
	@Autowired
	BloodStorageRepository storageRepo;
	@Autowired
	MedicalCenterRepository centerRepo;
	@Autowired
	UserRepository userRepo;
	

	
	public static void main(String[] args) {
		SpringApplication.run(BloodSimpleApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Address a1 = new Address(0, "1", "2", "3", "4", 0,0);
		addressRepo.save(a1);
	
	}

}
