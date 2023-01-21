package com.ftn.e2.isa.blood_simple.dto;

public class UpdatePasswordDTO {

    public String currentpassword;
    public String newpassword;
    public String repeatedpassword;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrentpassword() {
        return currentpassword;
    }

    public void setCurrentpassword(String currentpassword) {
        this.currentpassword = currentpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRepeatedpassword() {
        return repeatedpassword;
    }

    public void setRepeatedpassword(String repeatedpassword) {
        this.repeatedpassword = repeatedpassword;
    }
}
