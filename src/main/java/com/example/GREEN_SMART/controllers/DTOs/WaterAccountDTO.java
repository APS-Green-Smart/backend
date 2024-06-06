package com.example.GREEN_SMART.controllers.DTOs;

import java.util.Date;

public class WaterAccountDTO {
    private String id;
    private Date reference;
    private double consumption;
    private double bill;
    private String cnpjEnterprise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getReference() {
        return reference;
    }

    public void setReference(Date reference) {
        this.reference = reference;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getCnpjEnterprise() {
        return cnpjEnterprise;
    }

    public void setCnpjEnterprise(String cnpjEnterprise) {
        this.cnpjEnterprise = cnpjEnterprise;
    }
}
