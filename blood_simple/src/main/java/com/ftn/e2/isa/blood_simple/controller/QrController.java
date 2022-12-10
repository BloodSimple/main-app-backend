package com.ftn.e2.isa.blood_simple.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.e2.isa.blood_simple.model.Image;
import com.ftn.e2.isa.blood_simple.repository.ImageRepository;
import com.ftn.e2.isa.blood_simple.service.QrService;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.ImageUtility;

@RestController
@RequestMapping(value="api/read-qr")
public class QrController {
	
	@Autowired
    private QrService qrCodeService;
	@Autowired
	ImageRepository imageRepo;
	 @GetMapping(path = {"/{name}"})
	    public ResponseEntity<String[]> getAppointmentViaQR(@PathVariable("name") String name) throws IOException {
		 
		 
		 Image dbImage = imageRepo.getByName(name);
		 String content = qrCodeService.readQRCode(ImageUtility.decompressImage(dbImage.getImage()));
		 String[] contentDisplay = qrCodeService.contentToDisplay(content);
		 return new ResponseEntity<>(contentDisplay, HttpStatus.OK);
	 }
	 
	 
}

