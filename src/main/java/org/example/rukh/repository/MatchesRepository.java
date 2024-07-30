package org.example.rukh.repository;

import org.example.rukh.model.Matches;
import org.example.rukh.model.News;
import org.example.rukh.model.Team;
import org.example.rukh.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer> {
    @Query(value = "SELECT * FROM matches m WHERE m.id=:id", nativeQuery = true)
    Matches getMatchesById(@Param("id") int id);
    @Query(value = "SELECT * FROM matches m WHERE m.discipline=:discipline", nativeQuery = true)
    List<Matches> getMatchesByDiscipline(@Param("discipline") String discipline);
    List<Matches> getMatchesByTournament(Tournament tournament);
    List<Matches> getMatchesByTeam1OrTeam2(Team team1, Team team2);
    void deleteAllByTournament(Tournament tournament);
    boolean existsByTournament(Tournament tournament);
    boolean existsByTeam1OrTeam2(Team team1, Team team2);
}
