package org.example.rukh.repository;

import org.example.rukh.model.News;
import org.example.rukh.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value = "SELECT * FROM team t WHERE t.id=:id", nativeQuery = true)
    Team getTeamById(@Param("id") int id);

}
