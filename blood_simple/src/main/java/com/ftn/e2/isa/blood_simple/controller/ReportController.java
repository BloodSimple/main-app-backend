package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.CreateReportDTO;
import com.ftn.e2.isa.blood_simple.dto.MedicalCenterDTO;
import com.ftn.e2.isa.blood_simple.dto.MissAppointmentDTO;
import com.ftn.e2.isa.blood_simple.model.*;
import com.ftn.e2.isa.blood_simple.service.MedicalCenterService;
import com.ftn.e2.isa.blood_simple.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private MedicalCenterService medicalCenterService;

    @PutMapping(value = "/create-report", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> createAppointmentReport(@RequestBody CreateReportDTO newDto){

        Appointment foundAppointment = medicalCenterService.getAppointmentById(newDto.appointmentId);
        if (foundAppointment==null)
        {
            return new ResponseEntity<>("Appointment not found.", HttpStatus.BAD_REQUEST);
        }
        Report newReport = newDto.dto2Model(newDto, foundAppointment);
        BloodReportData bloodData = new BloodReportData(newDto.medicalCenterId, newDto.bloodAmount,newDto.bloodType);
        Equipment spentEquipment = newDto.getSpentEquipment();

        CreateReportError re = reportService.createAppointmentReport(newReport, bloodData, spentEquipment);
        if (re.status == true)
        {


            return new ResponseEntity<>(re.message, HttpStatus.BAD_REQUEST);
        }

        try{
//                System.out.println("POKUSAVA DA DODA REPORT VREME");
            foundAppointment = medicalCenterService.getAppointmentById(newDto.appointmentId);
            medicalCenterService.notifyUserLastDonation(foundAppointment.getStartTime(),foundAppointment.getUser().getId());
        }catch (Exception e)
        {

        }

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @PutMapping(value = "/user-miss-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> userMissAppointment(@RequestBody MissAppointmentDTO newDto){
//
        if(reportService.userMissAppointment(newDto))
        {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/appointment-condition-unfulfilled")
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> appointmentConditionUnfulfilled(@RequestBody MissAppointmentDTO newDto, HttpServletRequest request){

        if(reportService.appointmentConditionUnfulfilled(newDto))
        {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/appointment-condition-check")
    @PreAuthorize("hasRole('MEDICAL_ADMIN')")
    public ResponseEntity<Object> appointmentConditionCheck(@RequestBody Long id, HttpServletRequest request){

        String ret = reportService.appointmentConditionCheck(id);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }





}
