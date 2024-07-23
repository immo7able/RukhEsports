package org.example.rukh.repository;


import org.example.rukh.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query(value = "SELECT * FROM news n WHERE n.category=:discipline", nativeQuery = true)
    List<News> getNewsByDiscipline(@Param("discipline") String discipline);
    @Query(value = "SELECT * FROM news n WHERE n.category=:discipline AND n.id=:id", nativeQuery = true)
    News getNewsById(@Param("discipline") String discipline,@Param("id") int id);
    @Query(value = "SELECT * FROM news n WHERE n.id=:id", nativeQuery = true)
    News getNewsById(@Param("id") int id);

}
