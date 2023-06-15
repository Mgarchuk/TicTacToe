package com.company.battle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.company.battle"})
@EntityScan("com.company.common")
public class BattleServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(BattleServiceApp.class, args);
    }
}
