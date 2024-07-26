package org.example.rukh.repository;

import org.example.rukh.model.Matches;
import org.example.rukh.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Integer> {
    @Query(value = "SELECT * FROM matches m WHERE m.id=:id", nativeQuery = true)
    Matches getMatchesById(@Param("id") int id);
    void deleteAllByTournament(Tournament tournament);
    boolean existsByTournament(Tournament tournament);
}
