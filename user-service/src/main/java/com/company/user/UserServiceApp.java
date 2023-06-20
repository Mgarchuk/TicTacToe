package com.company.user;

import com.company.common.config.AuthProperties;
import com.company.common.models.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.company.user", "com.company.common"})
@EnableJpaRepositories(basePackages = "com.company.common")
@EntityScan("com.company.common")
@EnableConfigurationProperties(AuthProperties.class)
public class UserServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }
}
