package org.example.rukh.repository;

import org.example.rukh.model.News;
import org.example.rukh.model.Team;
import org.example.rukh.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value = "SELECT * FROM team t WHERE t.id=:id", nativeQuery = true)
    Team getTeamById(@Param("id") int id);
    @Query(value = "SELECT * FROM team t WHERE t.discipline=:discipline AND t.rukh=true", nativeQuery = true)
    Team getTeamByDisciplineAndRukh(@Param("discipline") String discipline);
    @Query(value = "SELECT * FROM team t WHERE t.discipline=:discipline", nativeQuery = true)
    List<Team> getTeamsByDiscipline(@Param("discipline") String discipline);

}
