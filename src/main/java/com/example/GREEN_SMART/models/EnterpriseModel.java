package com.example.GREEN_SMART.models;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class EnterpriseModel {

    @Id
    private String id;

    private String companyName;
    private String cnpj;
    private String email;
    private String password;

    @OneToMany(mappedBy = "enterprise")
    @JsonManagedReference
    private List<EnergyAccountModel> energyAccounts;

    @OneToMany(mappedBy = "enterprise")
    @JsonManagedReference
    private List<WaterAccountModel> waterAccounts;

    @OneToOne
    @JoinColumn(name = "water_goal_id", referencedColumnName = "id")
    private WaterAccountGoalModel waterGoal;

    @OneToOne
    @JoinColumn(name = "energy_goal_id", referencedColumnName = "id")
    private EnergyAccountGoalModel energyGoal;

    public EnterpriseModel() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EnergyAccountModel> getEnergyAccounts() {
        return energyAccounts;
    }

    public void setEnergyAccounts(List<EnergyAccountModel> energyAccounts) {
        this.energyAccounts = energyAccounts;
    }

    public List<WaterAccountModel> getWaterAccounts() {
        return waterAccounts;
    }

    public void setWaterAccounts(List<WaterAccountModel> waterAccounts) {
        this.waterAccounts = waterAccounts;
    }

    public WaterAccountGoalModel getWaterGoal() {
        return waterGoal;
    }

    public void setWaterGoal(WaterAccountGoalModel waterGoal) {
        this.waterGoal = waterGoal;
    }

    public EnergyAccountGoalModel getEnergyGoal() {
        return energyGoal;
    }

    public void setEnergyGoal(EnergyAccountGoalModel energyGoal) {
        this.energyGoal = energyGoal;
    }
}
