package com.ftn.e2.isa.blood_simple.controller;

import com.ftn.e2.isa.blood_simple.dto.DonationFormDTO;
import com.ftn.e2.isa.blood_simple.model.User;
import com.ftn.e2.isa.blood_simple.service.DonationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DonationFormController {

    @Autowired
    private DonationFormService donationFormService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/donationForm")
    public boolean saveDonationForm(@RequestBody DonationFormDTO message, @AuthenticationPrincipal User user, HttpServletRequest request) {
        return donationFormService.saveDonationForm(message, user, getSiteURL(request));
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
