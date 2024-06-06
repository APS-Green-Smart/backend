package com.example.GREEN_SMART.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GREEN_SMART.models.WaterAccountGoalModel;

@Repository
public interface WaterGoalModelRepository extends JpaRepository<WaterAccountGoalModel, Long> {
    Optional<WaterAccountGoalModel> findByCnpjEnterprise(String cnpjEnterprise);
}