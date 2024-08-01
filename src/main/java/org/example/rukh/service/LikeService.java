package org.example.rukh.service;

import jakarta.transaction.Transactional;
import org.example.rukh.model.Likes;
import org.example.rukh.model.News;
import org.example.rukh.model.User;
import org.example.rukh.repository.LikeRepository;
import org.example.rukh.repository.NewsRepository;
import org.example.rukh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private NewsRepository newsRepository;
    public boolean getLikesByNews(int id, UserDetails userDetails){
        try{
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->  new IllegalArgumentException("User not found"));
            News news = newsRepository.getNewsById(id);
            boolean likes = likeRepository.existsByNewsAndUser(news, user);
            if(likes)
                return true;
            else return false;
        }catch (Exception e){
            return false;
        }
    }
    @Transactional
    public String likeNews(int id, boolean liked, UserDetails userDetails) {
        try{
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->  new IllegalArgumentException("User not authorized"));
            News news = newsRepository.getNewsById(id);
            Likes like = new Likes();
            like.setUser(user);
            like.setNews(news);
            if(liked&&!likeRepository.existsByNewsAndUser(news, user)){
                likeRepository.save(like);
                System.out.println("Liked");
            }
            else {
                likeRepository.deleteByUserAndNews(user, news);
                System.out.println("Unliked");
            }
            return null;
        }
        catch (Exception e){
            return "Ошибка при создании: "+e.getMessage();
        }
    }
}
