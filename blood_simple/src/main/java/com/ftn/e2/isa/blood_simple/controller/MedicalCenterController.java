package com.ftn.e2.isa.blood_simple.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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

import com.ftn.e2.isa.blood_simple.dto.MedicalCenterDTO;
import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.MedicalCenterService;

@RestController
//@CrossOrigin(origins = "http://localhost:54372")
@RequestMapping(value = "api/centers")
public class MedicalCenterController {

	@Autowired
	MedicalCenterService medicalCenterService;

	@Autowired
	RegistrationService registrationService;

//	@GetMapping
//	public List<MedicalCenter> get(){
//		return service.getAll();
//	}
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalCenter>> getMedicalCenters(HttpServletRequest request){
		List<MedicalCenter> list = medicalCenterService.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalCenter> getMedicalCenter(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.get(id);
        if (mc == null) {
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mc, HttpStatus.OK);
	}

	@PutMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateMedicalCenter(@RequestBody MedicalCenterDTO centerDto){
		MedicalCenter mc = medicalCenterService.saveOrUpdate(centerDto);
		if(mc == null){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(mc, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalCenter> createMedicalCenter(@RequestBody MedicalCenterDTO newDto,HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.saveOrUpdate(newDto);
		if (mc==null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(mc, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> putAdminToCenter(@PathVariable String id, @RequestBody User admin, HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.getByName(id);
		if (mc == null && id != "")
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		/* TO JE BILO  -- admin = service.saveOrUpdateAdmin(admin); */
		boolean successfullyRegistered = registrationService.registerMedicalAdmin(admin, getSiteURL(request));
		if(!successfullyRegistered){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if(id != "") {
			mc.setAdmin(admin);
			if (mc.getAdmin() == null )
	        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			medicalCenterService.saveCenter(mc);
		}return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getCenterAdmin(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.get(id);
		if(mc==null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(mc.getAdmin(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> putAddressToCenter(@RequestBody Address address,@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.get(id);
		if(mc==null && id != 0)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		//service.saveOrUpdateAddress(address);	//ponavlja se u saveorupdate mc
		mc.setAddress(address);
		medicalCenterService.saveOrUpdate(new MedicalCenterDTO(mc));
		return new ResponseEntity<>(address, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getCenterAddress(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = medicalCenterService.get(id);
		if (mc==null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(mc.getAddress(), HttpStatus.OK);
	}
	
	@GetMapping(value="/selectadmin")
	public List<User> getFreeAdmins( HttpServletRequest request){
		return medicalCenterService.getFreeAdmins();
	}
	
	@GetMapping(value="/allusers")
	public List<User> getUsers(HttpServletRequest request){
		return medicalCenterService.getUsers();
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
