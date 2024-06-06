package com.example.GREEN_SMART.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GREEN_SMART.controllers.DTOs.EnergyAccountGoalDTO;
import com.example.GREEN_SMART.controllers.DTOs.WaterAccountGoalDTO;
import com.example.GREEN_SMART.models.EnergyAccountGoalModel;
import com.example.GREEN_SMART.models.GoalModel;
import com.example.GREEN_SMART.models.WaterAccountGoalModel;
import com.example.GREEN_SMART.repository.EnergyGoalModelRepository;
import com.example.GREEN_SMART.repository.WaterGoalModelRepository;

import jakarta.transaction.Transactional;

@Service
public class GoalService {

    @Autowired
    private WaterGoalModelRepository waterGoalModelRepository;

    @Autowired
    private EnergyGoalModelRepository energyGoalModelRepository;

    public GoalModel saveWaterGoal(WaterAccountGoalModel goal) {
        return waterGoalModelRepository.save(goal);
    }

    public GoalModel saveEnergyGoal(EnergyAccountGoalModel goal) {
        return energyGoalModelRepository.save(goal);
    }

    public Optional<WaterAccountGoalModel> getWaterGoalByEnterpriseAndType(String cnpj) {
        return waterGoalModelRepository.findByCnpjEnterprise(cnpj);
    }

    public Optional<EnergyAccountGoalModel> getEnergyGoalByEnterpriseAndType(String cnpj) {
        return energyGoalModelRepository.findByCnpjEnterprise(cnpj);
    }

    @Transactional
    public void editWaterGoal(WaterAccountGoalDTO waterGoalDTO) {
        Optional<WaterAccountGoalModel> waterGoalOptional = waterGoalModelRepository
                .findByCnpjEnterprise(waterGoalDTO.getCnpjEnterprise());
        if (!waterGoalOptional.isPresent()) {
            throw new RuntimeException("Water goal not found");
        }
        WaterAccountGoalModel waterGoal = waterGoalOptional.get();
        waterGoal.setCnpjEnterprise(waterGoalDTO.getCnpjEnterprise());
        waterGoal.setConsumptionGoal(waterGoalDTO.getConsumptionGoal());
        waterGoal.setBillGoal(waterGoalDTO.getBillGoal());
        waterGoalModelRepository.save(waterGoal);
    }

    @Transactional
    public void editEnergyGoal(EnergyAccountGoalDTO energyGoalDTO) {
        Optional<EnergyAccountGoalModel> energyGoalOptional = energyGoalModelRepository
                .findByCnpjEnterprise(energyGoalDTO.getCnpjEnterprise());
        if (!energyGoalOptional.isPresent()) {
            throw new RuntimeException("Energy goal not found");
        }
        EnergyAccountGoalModel energyGoal = energyGoalOptional.get();
        energyGoal.setCnpjEnterprise(energyGoalDTO.getCnpjEnterprise());
        energyGoal.setConsumptionGoal(energyGoalDTO.getConsumptionGoal());
        energyGoal.setBillGoal(energyGoalDTO.getBillGoal());
        energyGoalModelRepository.save(energyGoal);
    }
}
