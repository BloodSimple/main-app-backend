package com.ftn.e2.isa.blood_simple.model;


// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {

    private String role;
    private String accessToken;
    private Long expiresIn;
    private String email;
    private String personalId;
    private String name;
    private String surname;

    public UserTokenState(String role, String accessToken, Long expiresIn, String email, String personalId, String name, String surname) {
        this.role = role;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.email = email;
        this.personalId = personalId;
        this.name = name;
        this.surname = surname;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String uuid) {
        this.personalId = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
