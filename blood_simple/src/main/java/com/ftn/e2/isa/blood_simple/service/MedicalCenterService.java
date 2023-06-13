package com.ftn.e2.isa.blood_simple.service;

import java.time.LocalDateTime;
import java.util.*;

import com.ftn.e2.isa.blood_simple.dto.*;
import com.ftn.e2.isa.blood_simple.repository.*;
import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
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

    @Autowired
    GradesRepository gradeRepository;

	// Medical Center - get, getAll, saveOrUpdate, delete

	public MedicalCenter get(Long id) {
		return repo.findById(id).orElse(null);
	}

    public List<MedicalCenter> getAll() {
        return repo.findAll();
    }

    public List<GradeCenterDTO> getCenterWithGrades(String email) {

        List<GradeCenterDTO> list = new ArrayList<>();

        System.out.println("Trazi korisnika na mail: " + email);
        User foundUser = userRepo.findByEmail(email);

        Long foundId = foundUser.getId();
        System.out.println("Nadjen id: " + foundId);

        List<MedicalCenter> allCenters = repo.findAll();

        List<Grade> grades = gradeRepository.findAll();

        Long foundGrade = 0L;

        double countNumber = 0;
        double sumGrades = 0;
        for(MedicalCenter center : allCenters)
        {
            foundGrade = 0L;
            countNumber=0;
            sumGrades=0;
            for(Grade grade : grades)
            {
                if(center.getId()==grade.getCenterId())
                {
                    countNumber+=1;
                    sumGrades+=grade.getGrade();

                    if(grade.getUserId()==foundId)
                    {
                        foundGrade = grade.getGrade();
                    }
                }


            }

            GradeCenterDTO newDto = new GradeCenterDTO();
            if(countNumber>0 && sumGrades>0)
            {
                center.setGrade(sumGrades/countNumber);
            }
            newDto.center = center;
            newDto.grade = foundGrade;
            list.add(newDto);

        }


        return  list;
    }

    public boolean addGrade(String mail, String  id, String grade) {

        try {
            User foundUser = userRepo.findByEmail(mail);

            List<Appointment> allApps = appointmentRepository.findAll();


            Long centerId = Long.valueOf(id);


            boolean hadApp = false;
            for(Appointment a :allApps )
            {
                if(a.getOther()==foundUser.getId() && a.getMedicalCenter().getId()==centerId)
                {
                    if(a.getStatus()==AppointmentStatus.finished)
                    {
                        hadApp = true;
                    }
                }
            }
            if(hadApp==false)
            {
                System.out.println("nema pregleda!!!!!!!!!!!!!!!!");
                return false;
            }

            Long newValue = Long.valueOf(grade);

            Grade newGrade = new Grade();
            newGrade.setCenterId(centerId);
            newGrade.setUserId(foundUser.getId());
            newGrade.setGrade(newValue);

            //check if user already added grade
            boolean didGradeIt = false;
            Grade foundGrade = null;

            List<Grade> allGrades = gradeRepository.findAll();
            for(Grade g : allGrades)
            {
                if(g.getCenterId()==centerId && foundUser.getId()==g.getUserId())
                {
                    didGradeIt = true;
                    foundGrade = g;
                }
            }
            if(didGradeIt==true)
            {
                foundGrade.setGrade(newValue);
                gradeRepository.save(foundGrade);
            }
            else {
                gradeRepository.save(newGrade);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
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

    public void notifyUserLastDonation(LocalDateTime dt, Long userId)
    {
        Optional<User> u = userRepo.findById(userId);
        if(u.isPresent())
        {
            System.out.println("Setuje zadnji donaciju........");
            System.out.println("Setuje zadnji donaciju........");
            System.out.println("Setuje zadnji donaciju........");
            User a = u.get();
            a.setLastBloodDonation(dt);
            userRepo.save(a);
        }
    }
}

