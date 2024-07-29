package org.example.rukh.repository;

import org.example.rukh.model.Comment;
import org.example.rukh.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT * FROM comments c WHERE c.news_id=:id", nativeQuery = true)
    List<Comment> getCommentsById(@Param("id") int id);
    @Query(value = "SELECT * FROM comments c WHERE c.id=:id", nativeQuery = true)
    Comment getCommentById(@Param("id") int id);
    @Query(value = "SELECT * FROM comments c WHERE c.parent_comment_id=:id", nativeQuery = true)
    List<Comment> getCommentsByParentCommentId(int id);
    void deleteAllByNews(News news);
    boolean existsByNews(News news);
}
