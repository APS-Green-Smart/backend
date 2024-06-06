package com.example.GREEN_SMART.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.GREEN_SMART.controllers.DTOs.AccountCreationDTO;
import com.example.GREEN_SMART.controllers.DTOs.EnergyAccountDTO;
import com.example.GREEN_SMART.controllers.DTOs.ListAccountsDTO;
import com.example.GREEN_SMART.controllers.DTOs.WaterAccountDTO;
import com.example.GREEN_SMART.models.EnergyAccountGoalModel;
import com.example.GREEN_SMART.models.EnergyAccountModel;
import com.example.GREEN_SMART.models.EnterpriseModel;
import com.example.GREEN_SMART.models.WaterAccountGoalModel;
import com.example.GREEN_SMART.models.WaterAccountModel;
import com.example.GREEN_SMART.repository.EnergyAccountRepository;
import com.example.GREEN_SMART.repository.EnergyGoalModelRepository;
import com.example.GREEN_SMART.repository.EnterpriseRepository;
import com.example.GREEN_SMART.repository.WaterAccountRepository;
import com.example.GREEN_SMART.repository.WaterGoalModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;

@Service
public class AccountServices {

    @Autowired
    private WaterAccountRepository waterAccountRepository;

    @Autowired
    private EnergyAccountRepository energyAccountRepository;

    @Autowired
    private WaterGoalModelRepository waterGoalModelRepository;

    @Autowired
    private EnergyGoalModelRepository energyGoalModelRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccountServices.class);

    public WaterAccountModel createWaterAccount(AccountCreationDTO accountDTO) throws DataIntegrityViolationException {
        Date referenceDate = truncateDate(accountDTO.getReferenceDate());

        Optional<EnterpriseModel> enterpriseOptional = this.enterpriseRepository
                .findByCnpj(accountDTO.getCnpjEnterprise());

        if (!enterpriseOptional.isPresent()) {
            throw new DataIntegrityViolationException("Enterprise not found!");
        }

        EnterpriseModel enterprise = enterpriseOptional.get();
        logger.info("Enterprise found: {}", enterprise.getCompanyName());

        if (waterAccountRepository.existsByReferenceDateAndCnpjEnterprise(referenceDate, enterprise.getCnpj(),
                enterprise.getId())) {
            throw new DataIntegrityViolationException(
                    "A water account with the same date, enterprise and company already exists.");
        }

        WaterAccountModel waterAccount = new WaterAccountModel(referenceDate, accountDTO.getConsumption(),
                accountDTO.getBillAmount(), new Date(), accountDTO.getCnpjEnterprise(), enterprise);
        return waterAccountRepository.save(waterAccount);
    }

    public EnergyAccountModel createEnergyAccount(AccountCreationDTO accountDTO)
            throws DataIntegrityViolationException {
        Date referenceDate = truncateDate(accountDTO.getReferenceDate());

        Optional<EnterpriseModel> enterpriseOptional = this.enterpriseRepository
                .findByCnpj(accountDTO.getCnpjEnterprise());

        if (!enterpriseOptional.isPresent()) {
            throw new DataIntegrityViolationException("Enterprise not found!");
        }

        EnterpriseModel enterprise = enterpriseOptional.get();
        logger.info("Enterprise found: {}", enterprise.getCompanyName());

        if (energyAccountRepository.existsByReferenceDateAndCnpjEnterprise(referenceDate, enterprise.getCnpj(),
                enterprise.getId())) {
            throw new DataIntegrityViolationException(
                    "An energy account with the same date, enterprise and company already exists.");
        }

        EnergyAccountModel energyAccount = new EnergyAccountModel(referenceDate, accountDTO.getConsumption(),
                accountDTO.getBillAmount(), new Date(), accountDTO.getCnpjEnterprise(), enterprise);
        return energyAccountRepository.save(energyAccount);
    }

    public List<Map<String, Object>> listAllWaterAccounts(ListAccountsDTO listAccountsDTO) {
        List<WaterAccountModel> accounts = waterAccountRepository
                .findByCnpjEnterprise(listAccountsDTO.getCnpjEnterprise());
        return listAccountsDTO.includeGoals()
                ? combineWaterAccountsWithGoal(accounts, listAccountsDTO.getCnpjEnterprise())
                : convertWaterAccountsToDto(accounts);
    }

    public List<Map<String, Object>> listAllEnergyAccounts(ListAccountsDTO listAccountsDTO) {
        List<EnergyAccountModel> accounts = energyAccountRepository
                .findByCnpjEnterprise(listAccountsDTO.getCnpjEnterprise());
        return listAccountsDTO.includeGoals()
                ? combineEnergyAccountsWithGoal(accounts, listAccountsDTO.getCnpjEnterprise())
                : convertEnergyAccountsToDto(accounts);
    }

    private List<Map<String, Object>> convertWaterAccountsToDto(List<? extends WaterAccountModel> accounts) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        return accounts.stream().map(account -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", account.getId());
            map.put("date", dateFormat.format(account.getReference()));
            map.put("consumption", account.getConsumption());
            map.put("bill", account.getBill());
            map.put("cnpjEnterprise", account.getCnpjEnterprise());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> convertEnergyAccountsToDto(List<? extends EnergyAccountModel> accounts) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        return accounts.stream().map(account -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", account.getId());
            map.put("date", dateFormat.format(account.getReference()));
            map.put("consumption", account.getConsumption());
            map.put("bill", account.getBill());
            map.put("cnpjEnterprise", account.getCnpjEnterprise());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> combineWaterAccountsWithGoal(List<WaterAccountModel> accounts,
            String cnpjEnterprise) {
        Optional<WaterAccountGoalModel> optionalGoal = waterGoalModelRepository.findByCnpjEnterprise(cnpjEnterprise);
        WaterAccountGoalModel goal = optionalGoal.orElse(null);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

        return accounts.stream().map(account -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", account.getId());
            map.put("date", dateFormat.format(account.getReference()));
            map.put("consumption", account.getConsumption());
            map.put("bill", account.getBill());
            map.put("cnpjEnterprise", account.getCnpjEnterprise());

            if (goal != null) {
                map.put("consumptionGoal", goal.getConsumptionGoal());
                map.put("billGoal", goal.getBillGoal());
            } else {
                map.put("consumptionGoal", 0);
                map.put("billGoal", 0);
            }

            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> combineEnergyAccountsWithGoal(List<EnergyAccountModel> accounts,
            String cnpjEnterprise) {
        Optional<EnergyAccountGoalModel> optionalGoal = energyGoalModelRepository.findByCnpjEnterprise(cnpjEnterprise);
        EnergyAccountGoalModel goal = optionalGoal.orElse(null);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

        return accounts.stream().map(account -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", account.getId());
            map.put("date", dateFormat.format(account.getReference()));
            map.put("consumption", account.getConsumption());
            map.put("bill", account.getBill());
            map.put("cnpjEnterprise", account.getCnpjEnterprise());

            if (goal != null) {
                map.put("consumptionGoal", goal.getConsumptionGoal());
                map.put("billGoal", goal.getBillGoal());
            } else {
                map.put("consumptionGoal", 0);
                map.put("billGoal", 0);
            }

            return map;
        }).collect(Collectors.toList());
    }

    private Date truncateDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @Transactional
    public void editWaterAccount(WaterAccountDTO waterAccountDTO) {
        Optional<WaterAccountModel> waterAccountOptional = waterAccountRepository.findById(waterAccountDTO.getId());
        if (!waterAccountOptional.isPresent()) {
            throw new RuntimeException("Water account not found");
        }
        WaterAccountModel waterAccount = waterAccountOptional.get();
        waterAccount.setReference(waterAccountDTO.getReference());
        waterAccount.setConsumption(waterAccountDTO.getConsumption());
        waterAccount.setBill(waterAccountDTO.getBill());
        waterAccount.setCnpjEnterprise(waterAccountDTO.getCnpjEnterprise());
        waterAccountRepository.save(waterAccount);
    }

    @Transactional
    public void editEnergyAccount(EnergyAccountDTO energyAccountDTO) {
        Optional<EnergyAccountModel> energyAccountOptional = energyAccountRepository.findById(energyAccountDTO.getId());
        if (!energyAccountOptional.isPresent()) {
            throw new RuntimeException("Energy account not found");
        }
        EnergyAccountModel energyAccount = energyAccountOptional.get();
        energyAccount.setReference(energyAccountDTO.getReference());
        energyAccount.setConsumption(energyAccountDTO.getConsumption());
        energyAccount.setBill(energyAccountDTO.getBill());
        energyAccount.setCnpjEnterprise(energyAccountDTO.getCnpjEnterprise());
        energyAccountRepository.save(energyAccount);
    }
}
