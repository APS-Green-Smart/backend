package com.example.GREEN_SMART.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GoalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idEnterprise", nullable = false)
    private String idEnterprise;

    private String cnpjEnterprise;
    private double consumptionGoal;
    private double billGoal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEnterprise() {
        return idEnterprise;
    }

    public void setIdEnterprise(String idEnterprise) {
        this.idEnterprise = idEnterprise;
    }

    public String getCnpjEnterprise() {
        return cnpjEnterprise;
    }

    public void setCnpjEnterprise(String cnpjEnterprise) {
        this.cnpjEnterprise = cnpjEnterprise;
    }

    public double getConsumptionGoal() {
        return consumptionGoal;
    }

    public void setConsumptionGoal(double consumptionGoal) {
        this.consumptionGoal = consumptionGoal;
    }

    public double getBillGoal() {
        return billGoal;
    }

    public void setBillGoal(double billGoal) {
        this.billGoal = billGoal;
    }
}
