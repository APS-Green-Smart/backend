package com.example.GREEN_SMART.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.GREEN_SMART.controllers.DTOs.RegistrationDTO;
import com.example.GREEN_SMART.models.EnergyAccountGoalModel;
import com.example.GREEN_SMART.models.EnterpriseModel;
import com.example.GREEN_SMART.models.WaterAccountGoalModel;
import com.example.GREEN_SMART.repository.EnterpriseRepository;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GoalService goalService;

    public EnterpriseModel saveUser(RegistrationDTO user) {

        Optional<EnterpriseModel> existingByEmail = enterpriseRepository.findByEmail(user.getEmail());
        if (existingByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        }

        Optional<EnterpriseModel> existingByCnpj = enterpriseRepository.findByCnpj(user.getCnpj());
        if (existingByCnpj.isPresent()) {
            throw new IllegalArgumentException("CNPJ already in use.");
        }

        EnterpriseModel enterprise = new EnterpriseModel();
        enterprise.setCnpj(user.getCnpj());
        enterprise.setCompanyName(user.getCompanyName());
        enterprise.setEmail(user.getEmail());
        enterprise.setPassword(passwordEncoder.encode(user.getPassword()));

        EnterpriseModel saved = enterpriseRepository.save(enterprise);

        WaterAccountGoalModel waterGoal = new WaterAccountGoalModel();
        waterGoal.setIdEnterprise(saved.getId());
        waterGoal.setCnpjEnterprise(saved.getCnpj());
        waterGoal.setConsumptionGoal(0);
        waterGoal.setBillGoal(0);
        this.goalService.saveWaterGoal(waterGoal);

        EnergyAccountGoalModel energyGoal = new EnergyAccountGoalModel();
        energyGoal.setIdEnterprise(saved.getId());
        energyGoal.setCnpjEnterprise(saved.getCnpj());
        energyGoal.setConsumptionGoal(0);
        energyGoal.setBillGoal(0);
        this.goalService.saveEnergyGoal(energyGoal);

        return saved;
    }

    public Optional<EnterpriseModel> findByEmail(String email) {

        Optional<EnterpriseModel> enterprise = this.enterpriseRepository.findByEmail(email);
        return enterprise;
    }

}
