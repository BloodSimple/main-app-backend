package com.ftn.e2.isa.blood_simple.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.e2.isa.blood_simple.model.Image;
import com.ftn.e2.isa.blood_simple.repository.ImageRepository;

import utils.ImageUtility;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin() 
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload/image")
    public ResponseEntity<Boolean> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {

        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @GetMapping(path = {"/get/image/info/{name}"})
//    public Image getImageDetails(@PathVariable("name") String name) throws IOException {
//
//        final Optional<Image> dbImage = imageRepository.findByName(name);
//
//        return Image.builder()
//                .name(dbImage.get().getName())
//                .type(dbImage.get().getType())
//                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
//    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Image dbImage = imageRepository.getByName(name);
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}
