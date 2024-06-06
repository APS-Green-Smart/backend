package com.example.GREEN_SMART.controllers.DTOs;

public class ListAccountsDTO {
    private String cnpjEnterprise;
    private boolean includeGoals;

    public String getCnpjEnterprise() {
        return this.cnpjEnterprise;
    }

    public void setCnpjEnterprise(String CnpjEnterprise) {
        this.cnpjEnterprise = CnpjEnterprise;
    }

    public boolean includeGoals() {
        return includeGoals;
    }

    public void setIncludeGoals(boolean includeGoals) {
        this.includeGoals = includeGoals;
    }
}
