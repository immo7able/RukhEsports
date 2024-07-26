package org.example.rukh.controller;

import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.service.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchesController {
    @Autowired
    private MatchesService matchesService;
    @GetMapping("/")
    public ResponseEntity<List<MatchesDTO>> getAllMatches() {
        List<MatchesDTO> Matches = matchesService.getAllMatches();
        return ResponseEntity.ok(Matches);
    }
}
