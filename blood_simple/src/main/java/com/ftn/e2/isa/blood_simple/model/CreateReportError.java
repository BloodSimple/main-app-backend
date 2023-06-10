package com.ftn.e2.isa.blood_simple.model;

public class CreateReportError {

    public boolean status;
    public String message;

    public CreateReportError(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
