package com.example.GREEN_SMART.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GREEN_SMART.controllers.DTOs.AccountCreationDTO;
import com.example.GREEN_SMART.controllers.DTOs.EnergyAccountDTO;
import com.example.GREEN_SMART.controllers.DTOs.ListAccountsDTO;
import com.example.GREEN_SMART.controllers.DTOs.WaterAccountDTO;
import com.example.GREEN_SMART.models.EnergyAccountModel;
import com.example.GREEN_SMART.models.WaterAccountModel;
import com.example.GREEN_SMART.services.AccountServices;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @PostMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/water/create")
    public WaterAccountModel createWaterAccount(@RequestBody AccountCreationDTO accountDTO) {
        return accountServices.createWaterAccount(accountDTO);
    }

    @PostMapping("/energy/create")
    public EnergyAccountModel createEnergyAccount(@RequestBody AccountCreationDTO accountDTO) {
        return accountServices.createEnergyAccount(accountDTO);
    }

    @PostMapping("/water/list")
    public List<Map<String, Object>> listWaterAccounts(@RequestBody ListAccountsDTO filter) {
        return accountServices.listAllWaterAccounts(filter);
    }

    @PostMapping("/energy/list")
    public List<Map<String, Object>> listEnergyAccounts(@RequestBody ListAccountsDTO filter) {
        return accountServices.listAllEnergyAccounts(filter);
    }

    @PutMapping("/water/edit")
    public ResponseEntity<?> editWaterAccount(@RequestBody WaterAccountDTO waterAccountDTO) {
        try {
            accountServices.editWaterAccount(waterAccountDTO);
            return ResponseEntity.ok("Water account updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating water account: " + e.getMessage());
        }
    }

    @PutMapping("/energy/edit")
    public ResponseEntity<?> editEnergyAccount(@RequestBody EnergyAccountDTO energyAccountDTO) {
        try {
            accountServices.editEnergyAccount(energyAccountDTO);
            return ResponseEntity.ok("Energy account updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating energy account: " + e.getMessage());
        }
    }
}
