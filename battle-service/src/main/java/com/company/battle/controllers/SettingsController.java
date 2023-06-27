package com.company.battle.controllers;

import com.company.common.dtos.SettingsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/settings")
public class SettingsController {

    @GetMapping("/{id}")
    ResponseEntity<SettingsDto> getSettingsById(@PathVariable UUID id) {
        return null;
    }

    @PostMapping("/default")
    ResponseEntity<SettingsDto> addDefaultSettings(@RequestBody SettingsDto settingsDto) {
        return null;
    }

    @PostMapping("/custom")
    ResponseEntity<SettingsDto> addCustomSettings(@RequestBody SettingsDto settingsDto) {
        return null;
    }

    @PutMapping("/change-settings")
    ResponseEntity<SettingsDto> changeSettings(@RequestBody SettingsDto settingsDto) {
        return null;
    }

    @PutMapping("/change-settings-to-default")
    ResponseEntity<SettingsDto> resetSettingsToDefault() {
        return null;
    }
}
