package com.ftn.e2.isa.blood_simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.e2.isa.blood_simple.model.Appointment;
import com.ftn.e2.isa.blood_simple.repository.AppointmentRepository;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class QrService {
	private Logger logger = LoggerFactory.getLogger(QrService.class);
	
	@Autowired 
	AppointmentRepository appointmentRepo;
	
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    
    public String readQRCode(byte[] qrCodeBytes) {
        try {
        	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(qrCodeBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
            BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
            MultiFormatReader multiFormatReader = new MultiFormatReader();

            Result result = multiFormatReader.decode(binaryBitmap);
            String qrCodeText = result.getText();
            return qrCodeText;
        } catch (IOException | NotFoundException ex) {
            logger.error("Error during reading QR code image", ex);
        }
        return null;
    }
    
    public String[] contentToDisplay(String content) {
    	String[] display = new String[5];			// ime, prezime, jmbg, vreme pocetka, trajanje, ime centra 
    	display = content.split(";");
    	return display;
    }
    
    public Appointment findAppointmentByContentData(String id, String dateString) {
    	//String[] display = this.contentToDisplay(content);
    	try {
    		Date date = (Date) formatter.parse(dateString);
        	return appointmentRepo.findAppointmentByData(id,date);

    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
    }
    
}