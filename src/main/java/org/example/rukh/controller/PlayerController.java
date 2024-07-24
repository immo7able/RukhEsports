package org.example.rukh.controller;

import org.example.rukh.model.DTO.PlayerDTO;
import org.example.rukh.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping("/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }
}
