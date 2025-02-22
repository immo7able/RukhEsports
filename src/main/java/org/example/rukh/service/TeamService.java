package org.example.rukh.service;

import org.example.rukh.model.DTO.PlayerDTO;
import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.model.DTO.TournamentDTO;
import org.example.rukh.model.Player;
import org.example.rukh.model.Team;
import org.example.rukh.model.Tournament;
import org.example.rukh.repository.PlayerRepository;
import org.example.rukh.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    public List<TeamDTO> getAllTeams() {
        List<Team> teamList = teamRepository.findAll();
        return teamList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public TeamDTO getTeamByDisciplineAndRukh(String discipline) {
        discipline=discipline.toLowerCase();
        Team team = teamRepository.getTeamByDisciplineAndRukh(discipline);
        if(team!=null){
            return convertToDTO(team);
        }
        else return null;
    }
    public List<TeamDTO> getTeamsByDiscipline(String discipline) {
        discipline=discipline.toLowerCase();
        List<Team> teams = teamRepository.getTeamsByDiscipline(discipline);
        return teams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setImg("/uploads/"+team.getImg());
        teamDTO.setDiscipline(team.getDiscipline());
        teamDTO.setRukh(team.isRukh());
        teamDTO.setPlayers(playerRepository.getPlayerByTeam(team).stream().map(this::convertToDTO).collect(Collectors.toList()));
        return teamDTO;
    }
    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setContent(player.getContent());
        playerDTO.setImg("/uploads/"+player.getImg());
        playerDTO.setSocialMediaLinks(player.getSocialMediaLinks());
        playerDTO.setTeam(player.getTeam());
        playerDTO.setNickname(player.getNickname());
        return playerDTO;
    }
}
