package com.ftn.e2.isa.blood_simple.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.dto.MedicalCenterDTO;
import com.ftn.e2.isa.blood_simple.model.Address;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.RoleENUM;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.repository.AddressRepository;
import com.ftn.e2.isa.blood_simple.repository.MedicalCenterRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;

@Service
public class MedicalCenterService {

	@Autowired
	MedicalCenterRepository repo;
	@Autowired
	AddressRepository addRepo;
	@Autowired
	UserRepository userRepo;
	
	/////////////////////////////////////////////
	public MedicalCenter get(Long id) {
		return repo.findById(id).orElse(null);
	}
	public List<MedicalCenter> getAll(){
		return repo.findAll();
	}
	public MedicalCenter saveOrUpdate(MedicalCenterDTO newDto) {
		Address address = saveOrUpdateAddress(newDto.getAddress());
		//User admin = saveOrUpdateAdmin(newDto.getAdmin());
		if (address != null /* && admin != null */ ) {		//mora imati adresu, moze i bez admina
 			MedicalCenter mc = new MedicalCenter(newDto);
 			//mc.setAddress(address);
 			//mc.setAdmin(admin);
			return repo.save(mc);
		}
		return null;
		
	}
	public Address saveOrUpdateAddress(Address address) {
		if (address == null)
			return null;
		for(Address a : addRepo.findAll()) {
			if(a.getStreet() == address.getStreet() && a.getNumber() == address.getNumber() && a.getCity()==address.getCity()) {
				return a;
			}
		}
		return addRepo.save(address);
	}
	public User saveOrUpdateAdmin(User admin) {
		if (admin == null || admin.getRole()!=RoleENUM.MEDICAL_ADMIN)
			return null;
		for(MedicalCenter mc : repo.findAll()) {
			if (  (mc.getAdmin() != null) && mc.getAdmin().getId().equals(admin.getId()) )
				return null;
		}
		if (admin.getAddress() != null) {
			Address address = saveOrUpdateAddress(admin.getAddress());
			admin.setAddress(address);
		}
		return userRepo.save(admin);
	}
	public void delete(Long id) {
		repo.deleteById(id);
	}
	public boolean addMedicalStaff( Long mcId, User staff) {
		MedicalCenter mc = repo.findById(mcId).orElse(null);
		if (mc != null && staff.getRole()==RoleENUM.MEDICAL_ADMIN) {
			mc.getMedicalStaff().add(staff);
			repo.save(mc);
			return true;
		}
		return false;			
	}
	public boolean removeMedicalStaff(Long mcId, User staff) {
		MedicalCenter mc = repo.findById(mcId).orElse(null);
		for(User u : mc.getMedicalStaff()) {
			if(u.getId() == staff.getId()) {
				mc.getMedicalStaff().remove(u);
				repo.save(mc);
				return true;
			}
			return false;
		}
		return false;
	}
	
	public List<User> getFreeAdmins(){
		return userRepo.getFreeAdmins();
	}
	
	public List<User> getUsers(){
		return userRepo.getUsers();
	}
	//DTO/////////////////////////////////////////////
	public MedicalCenterDTO getDto(Long id) {
		return new MedicalCenterDTO(repo.findById(id).orElse(null));
	}
	
	public List<MedicalCenterDTO> getAllDto(){
		List<MedicalCenter> modelList = repo.findAll();
		List<MedicalCenterDTO> dtoList = new ArrayList<MedicalCenterDTO>();
		for(MedicalCenter model : modelList) {
			dtoList.add(new MedicalCenterDTO(model));
		}
		return dtoList;
	}
	
	public void saveCenter(MedicalCenter mc) {
		 repo.save(mc);
	}
	public MedicalCenter getByName(String id) {
		// TODO Auto-generated method stub
		return repo.getByName(id);
	}
}
