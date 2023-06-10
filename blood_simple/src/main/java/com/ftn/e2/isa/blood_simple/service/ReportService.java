package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.dto.MissAppointmentDTO;
import com.ftn.e2.isa.blood_simple.dto.UserDTO;
import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BloodStorageRepository bloodStorageRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DonationFormRepository donationFormRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MedicalCenterRepository medicalCenterRepository;




    //    userMissAppointment
    public boolean userMissAppointment(MissAppointmentDTO missAppointmentDTO) {
        Long appointmentId = missAppointmentDTO.appointmentId;
        Optional<Appointment> app = appointmentRepository.findById(appointmentId);

        if (app.isPresent()) {
            Appointment appointment = app.get();
            appointment.setStatus(AppointmentStatus.missed);
        appointmentRepository.save(appointment);
        } else {
            return false;
        }
        return true;
    }


    public boolean appointmentConditionUnfulfilled(MissAppointmentDTO missAppointmentDTO)
    {
        Long appointmentId = missAppointmentDTO.appointmentId;
        Optional<Appointment> app = appointmentRepository.findById(appointmentId);

        if (app.isPresent()) {
            Appointment appointment = app.get();
            appointment.setStatus(AppointmentStatus.unfulfilled_conditions);
            appointmentRepository.save(appointment);
        } else {
            return false;
        }
        return true;
    }

    public CreateReportError createAppointmentReport(Report report, BloodReportData bloodData, Equipment spentEquipment) {


        Optional<MedicalCenter> foundMedicalCenter = medicalCenterRepository.findById(bloodData.medicalCenterId);
        MedicalCenter mc;
        if (foundMedicalCenter.isPresent()) {
            mc = foundMedicalCenter.get();
        } else {
            return new CreateReportError(true,"Medical Center not found");
        }

        // equipment save

        Optional<Equipment> optionalEquipment = equipmentRepository.findByMedicalCenter(mc);

        Equipment equipment;
        if (optionalEquipment.isPresent()) {
            equipment = optionalEquipment.get();

            equipment.setBloodLancet(equipment.getBloodLancet()-spentEquipment.getBloodLancet());

            equipment.setSbag150(equipment.getSbag150()-spentEquipment.getSbag150());
            equipment.setSbag400(equipment.getSbag400()-spentEquipment.getSbag400());
            equipment.setSbag600(equipment.getSbag600()-spentEquipment.getSbag600());

            equipment.setDbag150(equipment.getDbag150()-spentEquipment.getDbag150());
            equipment.setDbag400(equipment.getDbag400()-spentEquipment.getDbag400());
            equipment.setDbag600(equipment.getDbag600()-spentEquipment.getDbag600());

            equipment.setTbag150(equipment.getTbag150()-spentEquipment.getTbag150());
            equipment.setTbag400(equipment.getTbag400()-spentEquipment.getTbag400());
            equipment.setTbag600(equipment.getTbag600()-spentEquipment.getTbag600());

        } else {
            return new CreateReportError(true,"Equipment not found.");
        }


        // blood data save

        Optional<BloodStorage> optionalBloodStorage = bloodStorageRepository.findByMedicalCenter(mc);

        BloodStorage bloodStorage;
        if (optionalBloodStorage.isPresent()) {
            bloodStorage = optionalBloodStorage.get();

            if(bloodData.bloodType.equals("A+"))
            {
                bloodStorage.setStoredAP(bloodStorage.getStoredAP()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("A-"))
            {
                bloodStorage.setStoredAN(bloodStorage.getStoredAN()+bloodData.bloodAmount);
            }else if(bloodData.bloodType.equals("B+"))
            {
                bloodStorage.setStoredBP(bloodStorage.getStoredBP()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("B-"))
            {
                bloodStorage.setStoredBN(bloodStorage.getStoredBN()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("O+"))
            {
                bloodStorage.setStoredOP(bloodStorage.getStoredOP()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("O-"))
            {
                bloodStorage.setStoredON(bloodStorage.getStoredON()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("AB+"))
            {
                bloodStorage.setStoredABP(bloodStorage.getStoredABP()+bloodData.bloodAmount);
            } else if(bloodData.bloodType.equals("AB-"))
            {
                bloodStorage.setStoredABN(bloodStorage.getStoredABN()+bloodData.bloodAmount);
            }
        } else {
            return new CreateReportError(true,"BloodStorage not found.");
        }





        //repot,eq and blood save
        if (equipment.getBloodLancet()<0 || equipment.getSbag150()<0 || equipment.getSbag400()<0 || equipment.getSbag600()<0
                || equipment.getDbag150()<0 || equipment.getDbag400()<0 || equipment.getDbag600()<0
                || equipment.getTbag150()<0 || equipment.getTbag400()<0 || equipment.getTbag600()<0)
        {
            return new CreateReportError(true,"Spent equipment is more that in storage.");
        }


        //TODO: dodati da se appointment status promeni na finished
        equipmentRepository.save(equipment);
        bloodStorageRepository.save(bloodStorage);
        reportRepository.save(report);

        // Other code

        return new CreateReportError(false,"OK");
    }


}
