package org.example.rukh.controller;

import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.model.DTO.TournamentDTO;
import org.example.rukh.model.Team;
import org.example.rukh.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @GetMapping("/")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
    @GetMapping("/{discipline}")
    public ResponseEntity<TeamDTO> getTeamByDiscipline(@PathVariable("discipline") String discipline) {
        TeamDTO team = teamService.getTeamByDiscipline(discipline);
        return ResponseEntity.ok(team);
    }
}
