package com.ui.pojo;

import java.util.Map;

public class Config {
    
    // Maps "DEV", "QA", "UAT" to their respective Environment objects
    private Map<String, Environment> environments;

    public Map<String, Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Environment> environments) {
        this.environments = environments;
    }
}