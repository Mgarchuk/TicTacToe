package com.company.statistic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/statistic")
public class StatisticController {

    @GetMapping("/{id}")
    public Integer getRating(@PathVariable UUID userId) {
        return null;
    }
}
