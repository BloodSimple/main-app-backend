package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.model.Image;
import com.ftn.e2.isa.blood_simple.repository.ImageRepository;
import com.ftn.e2.isa.blood_simple.service.QrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.ImageUtility;

import java.io.IOException;

@RestController
@RequestMapping(value = "api/read-qr")
public class QrController {

    @Autowired
    ImageRepository imageRepo;
    @Autowired
    private QrService qrCodeService;

    @GetMapping(path = {"/{name}"})
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String[]> getAppointmentViaQR(@PathVariable("name") String name) throws IOException {


        Image dbImage = imageRepo.getByName(name);
        String content = qrCodeService.readQRCode(ImageUtility.decompressImage(dbImage.getImage()));
        String[] contentDisplay = qrCodeService.contentToDisplay(content);
        return new ResponseEntity<>(contentDisplay, HttpStatus.OK);
    }


}

