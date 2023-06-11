package com.ftn.e2.isa.blood_simple.controller;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.ftn.e2.isa.blood_simple.dto.*;
import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.BloodStorage;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.MedicalCenterService;
import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/centers")
public class MedicalCenterController {

    @Autowired
    MedicalCenterService medicalCenterService;

    @Autowired
    RegistrationService registrationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//	@GetMapping
//	public List<MedicalCenter> get(){
//		return service.getAll();
//	}

    @PostMapping(value = "/create-appointment-free", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<String> createAppointmentFree(@RequestBody NewAppointmentFree dto) {

        System.out.println("Pravi sifru");

//        User user = UserDTO.MapToUser(message);
//        System.out.println("Poslat korisnik sa sifrom:");
//        System.out.println(user.getPassword());
//        System.out.println("Pokusaj kodovanja:");
//        String sifra = passwordEncoder.encode("aA123456789!");
//        System.out.println(sifra);
//        return null;
        System.out.println("OVDE!!!!!!!!!!!!!!!!!!!!!!!!");
        String ret = medicalCenterService.createFreeAppointment(dto);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicalCenter>> getMedicalCenters(HttpServletRequest request) {
        List<MedicalCenter> list = medicalCenterService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalCenter> getMedicalCenter(@PathVariable Long id, HttpServletRequest request) {
        MedicalCenter mc = medicalCenterService.get(id);
        if (mc == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mc, HttpStatus.OK);
    }

    @GetMapping(value = "/dto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMedicalCenterDTOById(@PathVariable Long id) {
        MedicalCenterDTO mcDTO = medicalCenterService.getById(id);
        if (mcDTO == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mcDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateMedicalCenter(@RequestBody MedicalCenterDTO centerDto) {
        MedicalCenter mc = medicalCenterService.saveOrUpdate(centerDto);
        if (mc == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mc, HttpStatus.OK);
        }
    }

	@GetMapping(value="/bloodstore/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMedicalCenterBloodAmount(@PathVariable Long id){
		List<BloodStoreDTO> dto = medicalCenterService.getBloodStoreForCenter(id);
		if(dto == null)
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}



	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalCenter> createMedicalCenter(@RequestBody MedicalCenterDTO newDto,HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.saveOrUpdate(newDto);
		if (mc==null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		BloodStorage bs = new BloodStorage();
		bs.setMedicalCenter(mc);
		bs.setStoredAP(0);
        bs.setStoredAN(0);

		bs.setStoredABP(0);
        bs.setStoredABN(0);

		bs.setStoredBP(0);
        bs.setStoredBN(0);

		bs.setStoredOP(0);
        bs.setStoredON(0);

		medicalCenterService.saveOrUpdate(bs);
		return new ResponseEntity<>(mc, HttpStatus.CREATED);
	}

    @PutMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> putAdminToCenter(@PathVariable String id, @RequestBody User admin, HttpServletRequest request) {
        MedicalCenter mc = medicalCenterService.getByName(id);
        if (mc.equals(null) && !id.equals(""))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //admin = medicalCenterService.saveOrUpdateAdmin(admin);
        boolean successfullyRegistered = registrationService.registerMedicalAdmin(admin, getSiteURL(request));
        if (!successfullyRegistered) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (!id.equals("")) {
            mc.getMedicalAdmins().add(admin);
            if (mc.getMedicalAdmins().isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            medicalCenterService.saveCenter(mc);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> getCenterAdmin(@PathVariable Long id, HttpServletRequest request) {
        MedicalCenter mc = medicalCenterService.get(id);
        if (mc.equals(null))
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mc.getMedicalAdmins(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> putAddressToCenter(@RequestBody Address address, @PathVariable Long id, HttpServletRequest request) {
        MedicalCenter mc = medicalCenterService.get(id);
        if (mc == null && id != 0)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //service.saveOrUpdateAddress(address);	//ponavlja se u saveorupdate mc
        mc.setAddress(address);
        medicalCenterService.saveOrUpdate(new MedicalCenterDTO(mc));
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> getCenterAddress(@PathVariable Long id, HttpServletRequest request) {
        MedicalCenter mc = medicalCenterService.get(id);
        if (mc == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mc.getAddress(), HttpStatus.OK);
    }

    @GetMapping(value = "/selectadmin")
    public Set<User> getFreeAdmins(HttpServletRequest request) {
        return medicalCenterService.getFreeAdmins();
    }

    @GetMapping(value = "/allusers")
    public List<User> getUsers(HttpServletRequest request) {
        return medicalCenterService.getUsers();
    }

    @GetMapping(value="/allRegUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> list = medicalCenterService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }



}
