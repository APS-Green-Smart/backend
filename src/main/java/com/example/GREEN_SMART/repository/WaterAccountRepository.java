package com.example.GREEN_SMART.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.GREEN_SMART.models.WaterAccountModel;

@Repository
public interface WaterAccountRepository extends JpaRepository<WaterAccountModel, String> {

    List<WaterAccountModel> findByCnpjEnterprise(String cnpjEnterprise);

    @Query("SELECT COUNT(w) > 0 FROM WaterAccountModel w WHERE w.reference = :referenceDate AND w.enterprise.id = :idEnterprise AND w.cnpjEnterprise = :cnpjEnterprise")
    boolean existsByReferenceDateAndCnpjEnterprise(Date referenceDate, String cnpjEnterprise, String idEnterprise);
}
