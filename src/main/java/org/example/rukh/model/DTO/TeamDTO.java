package org.example.rukh.model.DTO;

import org.example.rukh.model.Player;
import org.example.rukh.model.Team;

import java.util.List;

public class TeamDTO {
    private Long id;

    private String name;
    private String discipline;
    private String img;
    private List<PlayerDTO> players;

    public TeamDTO(Team team) {
        this.id=team.getId();
        this.name=team.getName();
        this.discipline=team.getDiscipline();
        this.img=team.getImg();
    }
    public TeamDTO() {
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
