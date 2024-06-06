package com.example.GREEN_SMART.controllers.DTOs;

public class WaterAccountGoalDTO {
    private String idEnterprise;
    private String cnpjEnterprise;
    private double consumptionGoal;
    private double billGoal;

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
