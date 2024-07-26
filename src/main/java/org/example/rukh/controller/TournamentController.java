package org.example.rukh.controller;

import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.model.DTO.TournamentDTO;
import org.example.rukh.model.Matches;
import org.example.rukh.model.Tournament;
import org.example.rukh.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/")
    public ResponseEntity<List<TournamentDTO>> getAllTournaments() {
        List<TournamentDTO> tournaments = tournamentService.getAllTournaments();
        return ResponseEntity.ok(tournaments);
    }
    @GetMapping("/{discipline}")
    public ResponseEntity<List<TournamentDTO>> getTournamentsByDiscipline(@PathVariable("discipline") String discipline) {
        List<TournamentDTO> tournaments = tournamentService.getTournamentsByDiscipline(discipline);
        return ResponseEntity.ok(tournaments);
    }
    @GetMapping("/{discipline}/{id}")
    public ResponseEntity<TournamentDTO> getTournamentById(@PathVariable("discipline") String discipline, @PathVariable("id") int id) {
        TournamentDTO tournament = tournamentService.getTournamentById(id);
        return ResponseEntity.ok(tournament);
    }
}
