package com.ftn.e2.isa.blood_simple.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ftn.e2.isa.blood_simple.dto.UserDTO;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/centers")
public class MedicalCenterController {

	@Autowired
	MedicalCenterService service;

//	@GetMapping
//	public List<MedicalCenter> get(){
//		return service.getAll();
//	}
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MedicalCenter>> getMedicalCenters(HttpServletRequest request){
		List<MedicalCenter> list = service.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalCenter> getMedicalCenter(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = service.get(id);
        if (mc == null) {
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mc, HttpStatus.OK);
	}

	@PutMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateMedicalCenter(@RequestBody MedicalCenterDTO centerDto){
		MedicalCenter mc = service.saveOrUpdate(centerDto);
		if(mc == null){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(mc, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MedicalCenter> createMedicalCenter(@RequestBody MedicalCenterDTO newDto,HttpServletRequest request){
		MedicalCenter mc = service.saveOrUpdate(newDto);
		if (mc==null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(mc, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> putAdminToCenter(@PathVariable String id, @RequestBody User admin, HttpServletRequest request){
		MedicalCenter mc = service.getByName(id);
		if (mc == null && id != "")
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		admin = service.saveOrUpdateAdmin(admin);
		if(id != "") {
			mc.setAdmin(admin);
			if (mc.getAdmin() == null )
	        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    		service.saveCenter(mc);
		}return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getCenterAdmin(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = service.get(id);
		if(mc==null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(mc.getAdmin(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> putAddressToCenter(@RequestBody Address address,@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = service.get(id);
		if(mc==null && id != 0)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		//service.saveOrUpdateAddress(address);	//ponavlja se u saveorupdate mc
		mc.setAddress(address);
		service.saveOrUpdate(new MedicalCenterDTO(mc));
		return new ResponseEntity<>(address, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getCenterAddress(@PathVariable Long id, HttpServletRequest request){
		MedicalCenter mc = service.get(id);
		if (mc==null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(mc.getAddress(), HttpStatus.OK);
	}
	
	@GetMapping(value="/selectadmin")
	public List<User> getFreeAdmins( HttpServletRequest request){
		return service.getFreeAdmins();
	}
	
	@GetMapping(value="/allusers")
	public List<User> getUsers(HttpServletRequest request){
		return service.getUsers();
	}
}
