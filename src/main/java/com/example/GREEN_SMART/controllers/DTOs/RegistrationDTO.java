package com.example.GREEN_SMART.controllers.DTOs;

public class RegistrationDTO {
    private String companyName;
    private String email;
    private String password;
    private String cnpj;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String companyName, String email, String password, String cnpj) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
