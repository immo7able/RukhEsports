package org.example.rukh.repository;

import org.example.rukh.model.News;
import org.example.rukh.model.Player;
import org.example.rukh.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    void deleteAllByTeam(Team team);
    boolean existsByTeam(Team team);
}
