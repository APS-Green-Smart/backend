package com.example.GREEN_SMART.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GREEN_SMART.models.EnergyAccountGoalModel;

@Repository
public interface EnergyGoalModelRepository extends JpaRepository<EnergyAccountGoalModel, Long> {
    Optional<EnergyAccountGoalModel> findByCnpjEnterprise(String cnpjEnterprise);
}
