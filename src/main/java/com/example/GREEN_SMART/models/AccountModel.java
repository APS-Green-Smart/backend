package com.example.GREEN_SMART.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class AccountModel {

    @Column(name = "reference")
    private Date reference;
    private double consumption;
    private double bill;
    private Date createdAt;
    private Date updatedAt;

    public AccountModel(Date reference, double consumption, double bill, Date createdAt) {
        this.reference = reference;
        this.consumption = consumption;
        this.bill = bill;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    protected AccountModel() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public Date getReference() {
        return reference;
    }

    public void setReference(Date reference) {
        this.reference = reference;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
