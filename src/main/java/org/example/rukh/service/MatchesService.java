package org.example.rukh.service;

import org.example.rukh.model.*;
import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.DTO.PlayerDTO;
import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.repository.MatchesRepository;
import org.example.rukh.repository.PlayerRepository;
import org.example.rukh.repository.TeamRepository;
import org.example.rukh.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TournamentRepository tournamentRepository;
    public List<MatchesDTO> getAllMatches() {
        List<Matches> matchesList = matchesRepository.findAll();
        return matchesList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<MatchesDTO> getMatchesByTournament(int id) {
        Tournament tournament = tournamentRepository.getTournamentById(id);
        List<Matches> matchesList = matchesRepository.getMatchesByTournament(tournament);
        return matchesList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<MatchesDTO> getMatchesByDiscipline(String discipline) {
        discipline = discipline.toLowerCase();
        List<Matches> matchesList = matchesRepository.getMatchesByDiscipline(discipline);
        return matchesList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public MatchesDTO getMatchesById(int id) {
        Matches matches = matchesRepository.getMatchesById(id);
        return convertToDTO(matches);
    }
    private MatchesDTO convertToDTO(Matches matches) {
        MatchesDTO matchesDTO = new MatchesDTO();
        matchesDTO.setId(matches.getId());
        matchesDTO.setTitle(matches.getTitle());
        matchesDTO.setDiscipline(matches.getDiscipline());
        matchesDTO.setImg("/uploads/"+matches.getImg());
        matchesDTO.setDate(matches.getDate());
        matchesDTO.setResult(matches.getResult());
        matchesDTO.setStatus(matches.getStatus());
        matchesDTO.setTournament(matches.getTournament());
        matchesDTO.setTeam1(convertToDTO(matches.getTeam1()));
        matchesDTO.setTeam2(convertToDTO(matches.getTeam2()));
        matchesDTO.setYoutubeUrl(matches.getYoutubeUrl());
        return matchesDTO;
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
    private TeamDTO convertToDTO(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setImg("/uploads/"+team.getImg());
        teamDTO.setDiscipline(team.getDiscipline());
        teamDTO.setPlayers(playerRepository.getPlayerByTeam(team).stream().map(this::convertToDTO).collect(Collectors.toList()));
        return teamDTO;
    }
}
