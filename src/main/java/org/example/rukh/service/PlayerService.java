package org.example.rukh.service;

import org.example.rukh.model.DTO.PlayerDTO;
import org.example.rukh.model.DTO.TeamDTO;
import org.example.rukh.model.Player;
import org.example.rukh.model.Team;
import org.example.rukh.repository.PlayerRepository;
import org.example.rukh.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().map(this::convertToDTO).collect(Collectors.toList());
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
