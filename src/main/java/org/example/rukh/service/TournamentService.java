package org.example.rukh.service;

import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.model.DTO.TournamentDTO;
import org.example.rukh.model.Team;
import org.example.rukh.model.Tournament;
import org.example.rukh.repository.TeamRepository;
import org.example.rukh.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    public List<TournamentDTO> getAllTournaments() {
        List<Tournament> tournamentsList = tournamentRepository.findAll();
        return tournamentsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<TournamentDTO> getTournamentsByDiscipline(String discipline) {
        discipline=discipline.toLowerCase();
        List<Tournament> tournamentsList = tournamentRepository.getTournamentByDiscipline(discipline);
        return tournamentsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public TournamentDTO getTournamentById(int id) {
        Tournament tournament = tournamentRepository.getTournamentById(id);
        return convertToDTO(tournament);
    }
    private TournamentDTO convertToDTO(Tournament tournament) {
        TournamentDTO tournamentDTO = new TournamentDTO();
        tournamentDTO.setId(tournament.getId());
        tournamentDTO.setName(tournament.getName());
        tournamentDTO.setContent(tournament.getContent());
        tournamentDTO.setImg("/uploads/"+tournament.getImg());
        tournamentDTO.setDiscipline(tournament.getDiscipline());
        tournamentDTO.setDate(tournament.getDate());
        tournamentDTO.setPrizepool(tournament.getPrizepool());
        tournamentDTO.setResult(tournament.getResult());
        tournamentDTO.setStatus(tournament.getStatus());
        return tournamentDTO;
    }
}
