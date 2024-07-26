package org.example.rukh.controller;

import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.Matches;
import org.example.rukh.service.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/{discipline}")
    public ResponseEntity<List<MatchesDTO>> getMatchesByDiscipline(@PathVariable(value="discipline") String discipline) {
        List<MatchesDTO> Matches = matchesService.getMatchesByDiscipline(discipline);
        return ResponseEntity.ok(Matches);
    }
    @GetMapping("/{discipline}/{id}")
    public ResponseEntity<MatchesDTO> getMatchesById(@PathVariable(value="discipline") String discipline, @PathVariable(value = "id") int id) {
        MatchesDTO Matches = matchesService.getMatchesById(id);
        return ResponseEntity.ok(Matches);
    }
    @GetMapping("/tournament/{id}")
    public ResponseEntity<List<MatchesDTO>> getMatchesByTournament( @PathVariable(value = "id") int id) {
        List<MatchesDTO> Matches = matchesService.getMatchesByTournament(id);
        return ResponseEntity.ok(Matches);
    }
}
