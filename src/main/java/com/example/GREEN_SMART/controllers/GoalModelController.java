package com.example.GREEN_SMART.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GREEN_SMART.controllers.DTOs.EnergyAccountGoalDTO;
import com.example.GREEN_SMART.controllers.DTOs.WaterAccountGoalDTO;
import com.example.GREEN_SMART.services.GoalService;

@RestController
@RequestMapping("/goals")
public class GoalModelController {

    @Autowired
    private GoalService goalService;

    @PutMapping("/update/water")
    public ResponseEntity<String> editWaterGoal(@RequestBody WaterAccountGoalDTO waterGoalDTO) {
        try {
            goalService.editWaterGoal(waterGoalDTO);
            return ResponseEntity.ok("Water goal updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update/energy")
    public ResponseEntity<String> editEnergyGoal(@RequestBody EnergyAccountGoalDTO energyGoalDTO) {
        try {
            goalService.editEnergyGoal(energyGoalDTO);
            return ResponseEntity.ok("Energy goal updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
