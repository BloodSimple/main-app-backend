package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.*;
import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.repository.AddressRepository;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.ftn.e2.isa.blood_simple.repository.ReportRepository;
import com.ftn.e2.isa.blood_simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    // Basic CRUD operations

    @Transactional
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }
        return usersDTO;
    }

    public UserDTO getUserById(Long Id) { // Database Id {1,2,3,..}
        User user = userRepository.findById(Id).orElse(null);
        if (user != null) {
            return new UserDTO(user);
        } else {
            return null;
        }
    }

    public UserDTO getUserByMail(String mail) { // Database Id {1,2,3,..}
        User user = userRepository.findByEmail(mail);
        if (user != null) {
            return new UserDTO(user);
        } else {
            return null;
        }
    }

    public UserDTO getUserByPersonalId(String personalId) { // JMBG in Serbia
        User user = userRepository.findByPersonalId(personalId);
        if (user != null) {
            return new UserDTO(user);
        } else {
            return null;
        }
    }

    public boolean updateUser(UserDTO updateUserDTO) {
        boolean status = userRepository.existsById(updateUserDTO.getId());
        if (status) {
            User userToUpdate = userRepository.findById(updateUserDTO.getId()).orElse(null);
            assert userToUpdate != null;
            Address address = addressRepository.findById(userToUpdate.getAddress().getId()).orElse(null);
            userToUpdate.setPassword(updateUserDTO.getPassword());
            userToUpdate.setName(updateUserDTO.getName());
            userToUpdate.setSurname(updateUserDTO.getSurname());
            userToUpdate.setGender(updateUserDTO.getGender());
            address.setId(updateUserDTO.getAddressId());
            address.setStreet(updateUserDTO.getAddressStreet());
            address.setNumber(updateUserDTO.getAddressNumber());
            address.setCity(updateUserDTO.getAddressCity());
            address.setCountry(updateUserDTO.getAddressCountry());
            userToUpdate.setPhoneNumber(updateUserDTO.getPhoneNumber());
            userToUpdate.setJob(updateUserDTO.getJob());
            userToUpdate.setBio(updateUserDTO.getBio());
            addressRepository.save(address);
            userRepository.save(userToUpdate);
        }
        return status;
    }

    public boolean updatePassword(UpdatePasswordDTO passwordDTO) {
        System.out.println("------------------Podaci");
        System.out.println("id " + passwordDTO.getId());
        System.out.println("sifra " + passwordDTO.getCurrentpassword());
        System.out.println("Podaci" + passwordDTO.getNewpassword());

        PasswordEncoder ps = new BCryptPasswordEncoder(10,new SecureRandom(new byte[0]));



        boolean status = userRepository.existsById(Long.valueOf(passwordDTO.getId()));
        System.out.println("*******************************************usao1");
        if (status) {
            System.out.println("*******************************************usao2222222222");
            Optional<User> optionalUserToUpdate = userRepository.findById(Long.valueOf(passwordDTO.getId()));
             if( optionalUserToUpdate.isPresent())
             {

                 System.out.println("*******************************************usao3");
                 User userToUpdate = optionalUserToUpdate.get();


            assert userToUpdate != null;
//            if (!userToUpdate.getPassword().equals(passwordEncoder.encode(passwordDTO.getCurrentpassword()))) {
//                System.out.println("*******************************************usao4444");
//                System.out.println(userToUpdate.getPassword());
//                System.out.println(passwordDTO.getCurrentpassword());
//                System.out.println(ps.encode(passwordDTO.getCurrentpassword()));
//                System.out.println("probajmo ovako");
//                System.out.println(ps.encode("12345"));
//                System.out.println(ps.encode("12345"));
//                System.out.println(ps.encode(passwordDTO.getNewpassword()));
//                System.out.println(ps.encode(passwordDTO.getCurrentpassword()));
//                return false;
//            }
            if (!passwordDTO.getNewpassword().equals(passwordDTO.getRepeatedpassword())) {
                System.out.println("*******************************************usao555555");
                return false;
            }
            userToUpdate.setPassword(passwordEncoder.encode(passwordDTO.getNewpassword()));
            userToUpdate.setFirst_login(false);
            userRepository.save(userToUpdate);

             }
             else
             {
                 return false;
             }
        }
        return status;
    }

    public boolean deleteUserByPersonalId(String personalId) {
        boolean status = userRepository.existsByPersonalId(personalId);
        if (status) {
            userRepository.deleteByPersonalId(personalId);
        }
        return status;
    }

    public List<UserDTO> getUsersByWithBloodDonations(Long id)
    {
        //ArrayList<User> foundUsers = new ArrayList<>(userRepository.findUsersWithBloodDonations(id));
        ArrayList<UserDTO> foundUsersDto = new ArrayList<>();
//        for(User u : foundUsers)
//        {
//            UserDTO dto = new UserDTO(u);
//            foundUsersDto.add(dto);
//        }
        return foundUsersDto;
    }

    public List<UserDonatedReportDTO> getUsersWhoDonatedBlood(Long id)
    {
//        List<Object[]> results = userRepository.findUsersAndAppointmentsWithFinishedAppointments(id);

        List<UserDonatedReportDTO> users = new ArrayList<>();

        List<Appointment> allAppointmentsForMC = appointmentRepository.getAppointmentsForCenter(id);
        List<Appointment> appointmentWithStatus = new ArrayList<>();

        for (Appointment a : allAppointmentsForMC)
        {

            if (a.getStatus() == AppointmentStatus.finished)
            {
                appointmentWithStatus.add(a);
            }
        }

        Set<Long> userIds = new HashSet<>();

        for (Appointment a: appointmentWithStatus)
        {
            userIds.add(a.getUser().getId());
        }

        for (Long userID : userIds) {

            Optional<User> u = userRepository.findById(userID);
            if(u.isPresent())
            {
                User found = u.get();
                UserDonatedReportDTO dto = new UserDonatedReportDTO();
                dto.id = found.getId();
                dto.job = found.getJob();
                dto.phoneNumber = found.getPhoneNumber();
                dto.name = found.getName();
                dto.address = found.getAddress().getCity() + " " + found.getAddress().getStreet() + " " + found.getAddress().getNumber();
                dto.lastName = found.getSurname();
                dto.latestBloodDonation = found.getLastBloodDonation().toString();
                dto.personalId = found.getPersonalId();
                dto.email = found.getEmail();
                users.add(dto);

            }
        }

        return users;
    }

    public List<AppointReportDTO> getUserAppointmentHistory(Long userId, Long medicalCenterId)
    {
        //naci sve appointment ja korisnka koji nisu free i taken i poslati ih nazad,

        List<Appointment> allAppointmentsForMC = appointmentRepository.getAppointmentsForCenter(medicalCenterId);

        List<Appointment> allAppointmentsForUser = new ArrayList<>();

        for (Appointment a : allAppointmentsForMC)
        {

            if (a.getUser()!=null)
            {
                if(a.getUser().getId()==userId && (a.getStatus()==AppointmentStatus.finished || a.getStatus()==AppointmentStatus.missed || a.getStatus()==AppointmentStatus.unfulfilled_conditions))
                {
                    allAppointmentsForUser.add(a);
                }
            }
        }

        List<AppointReportDTO> finalList = new ArrayList<>();

        for (Appointment a : allAppointmentsForUser)
        {
            Optional<Report> r = reportRepository.findById(a.getId());

            Report found = null;
            if(r.isPresent())
            {
                found = r.get();
            }
            AppointReportDTO dto = new AppointReportDTO();
            dto.appointment = a;
            dto.report = found;
            finalList.add(dto);

        }


        return finalList;
    }

    public boolean isFirstMedicalAdminLogin(String mail)
    {
        User foundUser = userRepository.findByEmail(mail);
        return foundUser.isFirst_login();
    }

    public Long getIdByMail(String mail)
    {
        User foundUser = userRepository.findByEmail(mail);
        return foundUser.getId();
    }

    public List<Appointment> getUserTakenAppointments(Long userId, Long medicalCenterId)
    {

        //naci sve appointment koje je korisnik zauzeo i da bi bili zapoceti
        List<Appointment> allAppointmentsForMC = appointmentRepository.getAppointmentsForCenter(medicalCenterId);

        List<Appointment> allAppointmentsForUser = new ArrayList<>();

        for (Appointment a : allAppointmentsForMC)
        {

            if (a.getUser()!=null)
            {
                if(a.getUser().getId()==userId && a.getStatus()==AppointmentStatus.taken)
                {
                    allAppointmentsForUser.add(a);
                }
            }
        }

        return allAppointmentsForUser;
    }

}
