package com.company.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.company.statistic")
public class StatisticServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceApp.class, args);
    }
}
