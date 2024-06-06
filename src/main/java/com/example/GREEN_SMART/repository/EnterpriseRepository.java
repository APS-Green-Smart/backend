package com.example.GREEN_SMART.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.GREEN_SMART.models.EnterpriseModel;

public interface EnterpriseRepository extends JpaRepository<EnterpriseModel, String> {
    @Override
    Optional<EnterpriseModel> findById(String id);

    Optional<EnterpriseModel> findByEmail(String email);

    Optional<EnterpriseModel> findByCnpj(String cnpj);

    @SuppressWarnings("unchecked")
    @Override
    EnterpriseModel save(EnterpriseModel enterpriseModel);
}
