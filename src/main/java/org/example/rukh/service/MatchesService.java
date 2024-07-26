package org.example.rukh.service;

import org.example.rukh.model.DTO.MatchesDTO;
import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.Matches;
import org.example.rukh.model.News;
import org.example.rukh.repository.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchesService {
    @Autowired
    private MatchesRepository matchesRepository;
    public List<MatchesDTO> getAllMatches() {
        List<Matches> matchesList = matchesRepository.findAll();
        return matchesList.stream().map(this::convertToDTO).collect(Collectors.toList());
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
        matchesDTO.setTeam1(matches.getTeam1());
        matchesDTO.setTeam2(matches.getTeam2());
        matchesDTO.setYoutubeUrl(matches.getYoutubeUrl());
        return matchesDTO;
    }
}
