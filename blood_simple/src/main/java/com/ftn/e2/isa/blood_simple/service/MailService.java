package com.ftn.e2.isa.blood_simple.service;

import com.ftn.e2.isa.blood_simple.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String fromAddress = "isa.mejl.12345@gmail.com";

    public void sendSuccessfulReservationEmail()
            throws MessagingException, UnsupportedEncodingException {

        String toAddress= "jankovicmaraja99@gmail.com";
        String senderName= "Blood Simple";
        String subject = "Your reservation is successful.";
        String content = "Dear [[name]],<br>"
                + "You made reservation for:<br>"
                + "Blood Simple.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", "Marija");

        helper.setText(content, true);

        mailSender.send(message);

    }
}
