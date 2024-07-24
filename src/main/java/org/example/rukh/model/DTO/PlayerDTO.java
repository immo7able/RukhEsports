package org.example.rukh.model.DTO;

import org.example.rukh.model.Team;

public class PlayerDTO {
    private Long id;
    private String nickname;
    private String name;
    private String content;
    private String img;
    private String socialMediaLinks;
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public void setSocialMediaLinks(String socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
