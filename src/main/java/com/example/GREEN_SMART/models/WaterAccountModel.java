package com.example.GREEN_SMART.models;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WaterAccountModel extends AccountModel {

    @Id
    private String id;
    private String cnpjEnterprise;
    private final String TYPE = "water";

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    @JsonBackReference
    private EnterpriseModel enterprise;

    public WaterAccountModel() {
        this.id = generateRandomString();
    }

    public WaterAccountModel(Date reference, double consumption, double bill, Date createdAt, String cnpjEnterprise,
            EnterpriseModel enterprise) {
        super(reference, consumption, bill, createdAt);
        this.cnpjEnterprise = cnpjEnterprise;
        this.enterprise = enterprise;
        this.id = generateRandomString();
    }

    private String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String getType() {
        return this.TYPE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpjEnterprise() {
        return cnpjEnterprise;
    }

    public void setCnpjEnterprise(String cnpjEnterprise) {
        this.cnpjEnterprise = cnpjEnterprise;
    }

    public EnterpriseModel getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseModel enterprise) {
        this.enterprise = enterprise;
    }
}
