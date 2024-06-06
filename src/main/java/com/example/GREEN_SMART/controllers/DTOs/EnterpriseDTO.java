package com.example.GREEN_SMART.controllers.DTOs;

public class EnterpriseDTO {
    private String id;
    private String companyName;
    private String cnpj;
    private String email;

    // Construtor, getters e setters
    public EnterpriseDTO(String id, String companyName, String cnpj, String email) {
        this.id = id;
        this.companyName = companyName;
        this.cnpj = cnpj;
        this.email = email;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }
}
