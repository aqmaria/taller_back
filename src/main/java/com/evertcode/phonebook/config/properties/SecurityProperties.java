package com.evertcode.phonebook.config.properties;

import org.springframework.context.annotation.Configuration;

public class SecurityProperties {
    private String jwtSignatureKey;
    private String username;
    private String password;

    public String getJwtSignatureKey() {
        return jwtSignatureKey;
    }

    public void setJwtSignatureKey(String jwtSignatureKey) {
        this.jwtSignatureKey = jwtSignatureKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
