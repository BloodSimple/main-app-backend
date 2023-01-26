package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.AppointmentDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleDTO;
import com.ftn.e2.isa.blood_simple.dto.AppointmentScheduleResponseDTO;
import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.model.MedicalCenter;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.security.TokenUtils;
import com.ftn.e2.isa.blood_simple.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Schedule controller", description = "The schedule API")
@RestController
@RequestMapping(value = "/api/centers", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    private TokenUtils tokenUtils;

    @Operation(summary = "Get all appointments", description = "Get all appointments", method="GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Appointment.class))))
    })
    @GetMapping(value = "/{id}/schedule")
    public ResponseEntity<List<Appointment>> getMedicalCenters(@PathVariable Long id, HttpServletRequest request) {
        List<Appointment> list = scheduleService.getAppointmentsByCenter(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/defineAppointment")
    public ResponseEntity<Object> createAppointment(@RequestBody AppointmentDTO newAppointmentDTO) {
        Appointment appointment = scheduleService.saveAppointment(newAppointmentDTO);
        if (appointment != null)
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/freeAppointments")
    public ResponseEntity<Object> getMedicalCenterWithAppointments(@RequestParam("startTime")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                           LocalDateTime startTime,
                                                                   @AuthenticationPrincipal User user) {
        try {
            List<MedicalCenter> list = scheduleService.getMedicalCenterWithAppointments(startTime, user);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something wrong happened.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "/createfreeappointment")
    public ResponseEntity<Object> createFreeAppointment(@RequestBody AppointmentDTO newAppointmentDTO) {
        Appointment appointment = scheduleService.saveAppointment(newAppointmentDTO);
        if (appointment != null)
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/freeappointments/{id}")
    public ResponseEntity<Object> getMedicalCenterFreeAppointments(@PathVariable Long id) {
        List<Appointment> list = scheduleService.getCenterFreeAppointments(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/scheduleAppointment")
    public ResponseEntity<AppointmentScheduleResponseDTO> scheduleAppointment(@RequestBody AppointmentScheduleDTO message, @AuthenticationPrincipal User user, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        AppointmentScheduleResponseDTO appointmentScheduleDTO = scheduleService.scheduleAppointment(message, user, getSiteURL(request));
        return new ResponseEntity<>(appointmentScheduleDTO, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/cancelAppointment")
    public ResponseEntity<AppointmentScheduleResponseDTO> cancelAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        AppointmentScheduleResponseDTO appointmentScheduleDTO = scheduleService.cancelAppointment(appointmentDTO);
        return new ResponseEntity<>(appointmentScheduleDTO, HttpStatus.OK);

    }

    @GetMapping(value = "myAppointments/{id}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable String id) {
        List<Appointment> list = scheduleService.getAppointmentsByUser(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
