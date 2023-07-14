package com.company.battle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.company.battle", "com.company.common"})
@EntityScan("com.company.common")
@EnableJpaRepositories(basePackages = {"com.company.battle", "com.company.common"})
public class BattleServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(BattleServiceApp.class, args);
    }
}
