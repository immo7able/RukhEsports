package org.example.rukh.service;

import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.model.Team;
import org.example.rukh.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    public List<TeamDTO> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setContent(team.getContent());
        teamDTO.setImg("/uploads/"+team.getImg());
        teamDTO.setDiscipline(team.getDiscipline());
        return teamDTO;
    }
}
