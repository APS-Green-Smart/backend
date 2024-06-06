package com.example.GREEN_SMART.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GREEN_SMART.controllers.DTOs.EnterpriseDTO;
import com.example.GREEN_SMART.controllers.DTOs.LoginDTO;
import com.example.GREEN_SMART.controllers.DTOs.RegistrationDTO;
import com.example.GREEN_SMART.models.EnterpriseModel;
import com.example.GREEN_SMART.repository.EnterpriseRepository;
import com.example.GREEN_SMART.services.EnterpriseService;
import com.example.GREEN_SMART.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EnterpriseService userService;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public EnterpriseModel registerUser(@RequestBody RegistrationDTO user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginDTO user) {
        Optional<EnterpriseModel> existingUser = userService.findByEmail(user.getEmail());

        if (!existingUser.isPresent()
                || !passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail(), existingUser.get().getCnpj());
    }

    @GetMapping("/getuser/{email}")
    public EnterpriseDTO getUser(@PathVariable String email) {

        Optional<EnterpriseModel> enterprise = this.enterpriseRepository.findByEmail(email);

        if (!enterprise.isPresent()) {
            throw new RuntimeException("Invalid email or password");
        }

        EnterpriseModel enterpriseModel = enterprise.get();
        EnterpriseDTO enterpriseDTO = new EnterpriseDTO(
                enterpriseModel.getId(),
                enterpriseModel.getCompanyName(),
                enterpriseModel.getCnpj(),
                enterpriseModel.getEmail());

        return enterpriseDTO;
    }

}
