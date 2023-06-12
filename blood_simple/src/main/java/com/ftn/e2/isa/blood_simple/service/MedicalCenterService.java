package com.ftn.e2.isa.blood_simple.service;

import java.util.*;

import com.ftn.e2.isa.blood_simple.dto.*;
import com.ftn.e2.isa.blood_simple.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class MedicalCenterService {

    @Autowired
    MedicalCenterRepository repo;
    @Autowired
    AddressRepository addRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    BloodStorageRepository bsRepo;
    @Autowired
    AppointmentRepository appointmentRepository;

	// Medical Center - get, getAll, saveOrUpdate, delete

	public MedicalCenter get(Long id) {
		return repo.findById(id).orElse(null);
	}

    public List<MedicalCenter> getAll() {
        return repo.findAll();
    }

    public MedicalCenter getByName(String id) {
        // TODO Auto-generated method stub
        return repo.getByName(id);
    }

    public MedicalCenter getCenterMedWorks(String mail)
    {
        User foundUser = userRepo.findByEmail(mail);
        Long idMedAdmina = foundUser.getId();
        List<MedicalCenter> allCenters = repo.findAll();
        System.out.println("user id");
        System.out.println(idMedAdmina);

        for (MedicalCenter mc : allCenters)
        {
            Set<User> allUsers =  mc.getMedicalStaff();
            for(User u : allUsers)
            {
                if(u.getId()==idMedAdmina)
                {
                    return mc;
                }
            }
        }
        return null;
    }

	public MedicalCenter saveOrUpdate(MedicalCenterDTO newDto) {
        Address address = saveOrUpdateAddress(newDto.getAddress());
        //User admin = saveOrUpdateAdmin(newDto.getAdmin());
        if (address != null /* && admin != null */) {        //mora imati adresu, moze i bez admina
            MedicalCenter mc = new MedicalCenter(newDto);
            //mc.setAddress(address);
            //mc.setAdmin(admin);
            return repo.save(mc);
        }
        return null;
    }

    public void saveCenter(MedicalCenter mc) {
        repo.save(mc);
    }

	public void delete(Long id) {
        repo.deleteById(id);
    }

	// Medical Center - special methods: addMedicalStaff

    public boolean addMedicalStaff(Long mcId, User staff) {
        MedicalCenter mc = repo.findById(mcId).orElse(null);
        if (mc != null && staff.getRole() == RoleENUM.MEDICAL_ADMIN) {
            mc.getMedicalStaff().add(staff);
            repo.save(mc);
            return true;
        }
        return false;
    }

    public boolean removeMedicalStaff(Long mcId, User staff) {
        MedicalCenter mc = repo.findById(mcId).orElse(null);
        for (User u : mc.getMedicalStaff()) {
            if (u.getId() == staff.getId()) {
                mc.getMedicalStaff().remove(u);
                repo.save(mc);
                return true;
            }
            return false;
        }
        return false;
    }

	// Medical Center DTO
	
    public MedicalCenterDTO getById(Long id) {
        MedicalCenter mc = repo.findOneById(id);
        if (mc != null) {
            return new MedicalCenterDTO(mc);
        } else {
            return null;
        }
    }

    public MedicalCenterDTO getDto(Long id) {
        return new MedicalCenterDTO(repo.findById(id).orElse(null));
    }

    public List<MedicalCenterDTO> getAllDto() {
        List<MedicalCenter> modelList = repo.findAll();
        List<MedicalCenterDTO> dtoList = new ArrayList<MedicalCenterDTO>();
        for (MedicalCenter model : modelList) {
            dtoList.add(new MedicalCenterDTO(model));
        }
        return dtoList;
    }

	// Address - saveOrUpdateAddress

	public Address saveOrUpdateAddress(Address address) {
        if (address == null)
            return null;
        for (Address a : addRepo.findAll()) {
            if (a.getStreet() == address.getStreet() && a.getNumber() == address.getNumber() && a.getCity() == address.getCity()) {
                return a;
            }
        }
        return addRepo.save(address);
    }

	// Blood Storage

	public List<BloodStorage> getAllBloodStore()
	{
		return bsRepo.findAll();
	}

    public BloodStorage saveOrUpdate(BloodStorage bs) {
        return bsRepo.save(bs);
    }

	// Blood Storage DTO

	public List<BloodStoreDTO> getBloodStoreForCenter(Long center_id)
	{
		List<BloodStorage> temp = getAllBloodStore();
		List<BloodStoreDTO> ret = new ArrayList<BloodStoreDTO>();

		for(BloodStorage store : temp)
		{
			if(store.getCenterId() == center_id)
			{

				ret.add(new BloodStoreDTO(store.getStoredAP(), store.getStoredAN(), store.getStoredBP(), store.getStoredBN(),
                        store.getStoredABP(), store.getStoredABN(), store.getStoredOP(), store.getStoredON()));
			}
		}
		return ret;
	}

	// Admin 

    public User saveOrUpdateAdmin(User admin) {
        if (admin.equals(null) || admin.getRole() != RoleENUM.MEDICAL_ADMIN)
            return null;
        for (MedicalCenter mc : repo.findAll()) {
            if (!mc.getMedicalAdmins().isEmpty()) {
                for (User a : mc.getMedicalAdmins()) {
                    if (a.getPersonalId().equals(admin.getPersonalId()))
                        return null;
                }
            }
        }
        if (admin.getAddress() != null) {
            Address address = saveOrUpdateAddress(admin.getAddress());
            admin.setAddress(address);
        }
        return userRepo.save(admin);
    }

	public Set<User> getFreeAdmins() {
        Set<User> freeAdmins = userRepo.getAllMedicalAdmins();
        if (!freeAdmins.isEmpty())
            for (Iterator<User> iterator = freeAdmins.iterator(); iterator.hasNext(); ) {
                User u = iterator.next();
                for (Iterator<MedicalCenter> iterator2 = repo.findAll().iterator(); iterator2.hasNext(); ) {
                    MedicalCenter mc = iterator2.next();
                    for (Iterator<User> iterator3 = mc.getMedicalAdmins().iterator(); iterator3.hasNext(); ) {
                        User u1 = iterator3.next();
                        if (u1.getPersonalId().equals(u.getPersonalId()))
                            iterator.remove();
                    }
                }
            }
        return freeAdmins;
    }

	// Users ??

    public List<User> getUsers() {
        return userRepo.getUsers();
    }

    public List<UserDTO> getAllUsers() {

        List<User> allUsers = userRepo.getUsers();
        List<UserDTO> dto = new ArrayList<>();
        for(User u : allUsers)
        {
            dto.add(new UserDTO(u));
        }
        return  dto;
    }

    public Appointment getAppointmentById(Long id)
    {
        Optional<Appointment> app = appointmentRepository.findById(id);

        if (app.isPresent()) {
            Appointment appointment = app.get();
            return appointment;
        }
        return null;
    }


    public String createFreeAppointment(NewAppointmentFree dto)
    {
        Appointment newAppointment = new Appointment();
        newAppointment.setReserved(false);

        List<User> allMedicalStaff = new ArrayList<>();



        for(MedicalStuffSelection u : dto.medicalStaff)
        {

            Optional<User> foundUser = userRepo.findById(Long.valueOf(u.id));
            if (foundUser.isPresent())
            {
                allMedicalStaff.add(foundUser.get());
//                System.out.println("Usao ovde");
//                return "Ima osoblja";
            }
        }

        System.out.println("Prosao");

        Optional<MedicalCenter> mc = repo.findById(dto.medicalCenterId);
        if(mc.isPresent())
        {
            MedicalCenter center = mc.get();
            newAppointment.setMedicalCenter(center);
            newAppointment.setMedicalStaff(allMedicalStaff);
            newAppointment.setStatus(AppointmentStatus.free);
            newAppointment.setDuration(dto.duration);
            newAppointment.setStartTime(dto.startTime);

            appointmentRepository.save(newAppointment);
            System.out.println("Sacuvano!!!!!!!");
            return "Appointment added.";
        }

        return "Server[error]";

    }
}
