package org.example.rukh.repository;

import org.example.rukh.model.News;
import org.example.rukh.model.Player;
import org.example.rukh.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    void deleteAllByTeam(Team team);
    boolean existsByTeam(Team team);
    @Query(value = "SELECT * FROM player p WHERE p.id=:id", nativeQuery = true)
    Player getPlayerById(@Param("id") int id);
}
