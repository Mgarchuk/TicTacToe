package com.company.battle.controllers;

import com.company.common.dtos.RoomDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    @GetMapping("/{link}")
    ResponseEntity<RoomDto> getRoomByLink(@PathVariable String link) {
        return null;
    }

    @PostMapping
    ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        return null;
    }

    @PutMapping("/join")
    ResponseEntity<RoomDto> joinRoom(@RequestBody String link) {
        return null;
    }

    @PutMapping("/leave")
    ResponseEntity<RoomDto> leaveRoom(@RequestBody RoomDto roomDto) {
        return null;
    }
}
