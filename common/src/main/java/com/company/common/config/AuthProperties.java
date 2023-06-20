package com.company.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.auth")
public class AuthProperties {

    private String tokenSecret;
    private long tokenExpMs;

    public long getTokenExpMs() {
        return tokenExpMs;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenExpMs(long tokenExpMs) {
        this.tokenExpMs = tokenExpMs;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
