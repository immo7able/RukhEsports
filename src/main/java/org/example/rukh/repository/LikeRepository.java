package org.example.rukh.repository;

import org.example.rukh.model.Likes;
import org.example.rukh.model.News;
import org.example.rukh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {
    boolean existsByNewsAndUser(News news, User user);
    void deleteByUserAndNews(User user, News news);
    int countLikesByNews(News news);
    boolean existsByNews(News news);
    void deleteAllByNews(News news);
}
