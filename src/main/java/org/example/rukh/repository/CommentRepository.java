package org.example.rukh.repository;

import org.example.rukh.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * FROM comments c WHERE c.news_id=:id", nativeQuery = true)
    List<Comment> getCommentsById(@Param("id") int id);
}
