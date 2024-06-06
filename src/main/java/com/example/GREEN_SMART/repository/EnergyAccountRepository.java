package com.example.GREEN_SMART.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.GREEN_SMART.models.EnergyAccountModel;

@Repository
public interface EnergyAccountRepository extends JpaRepository<EnergyAccountModel, String> {

    List<EnergyAccountModel> findByCnpjEnterprise(String cnpjEnterprise);

    @Query("SELECT COUNT(e) > 0 FROM EnergyAccountModel e WHERE e.reference = :referenceDate AND e.enterprise.id = :idEnterprise AND e.cnpjEnterprise = :cnpjEnterprise")
    boolean existsByReferenceDateAndCnpjEnterprise(Date referenceDate, String cnpjEnterprise, String idEnterprise);
}