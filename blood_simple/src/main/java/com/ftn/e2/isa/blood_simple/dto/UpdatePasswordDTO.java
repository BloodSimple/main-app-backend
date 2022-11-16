package com.ftn.e2.isa.blood_simple.dto;

public class UpdatePasswordDTO {

    private String id;
    public String currentpassword;
    public String newpassword;
    public String repeatedpassword;

    public String getId() {
        return id;
    }

    public String getCurrentpassword() {
        return currentpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public String getRepeatedpassword() {
        return repeatedpassword;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentpassword(String currentpassword) {
        this.currentpassword = currentpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public void setRepeatedpassword(String repeatedpassword) {
        this.repeatedpassword = repeatedpassword;
    }
}
