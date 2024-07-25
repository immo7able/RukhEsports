package org.example.rukh.repository;

import org.example.rukh.model.Team;
import org.example.rukh.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    @Query(value = "SELECT * FROM tournament t WHERE t.id=:id", nativeQuery = true)
    Tournament getTournamentById(@Param("id") int id);

}
