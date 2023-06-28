package com.company.battle.controllers;

import com.company.common.dtos.RoomDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    @GetMapping("/{link}")
    RoomDto getRoomByLink(@PathVariable String link) {
        return null;
    }

    @PostMapping
    RoomDto createRoom(@RequestBody RoomDto roomDto) {
        return null;
    }

    @PutMapping("/join")
    RoomDto joinRoom(@RequestBody String link) {
        return null;
    }

    @PutMapping("/leave")
    RoomDto leaveRoom(@RequestBody RoomDto roomDto) {
        return null;
    }
}
