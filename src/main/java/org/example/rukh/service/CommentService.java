package org.example.rukh.service;

import org.example.rukh.model.Comment;
import org.example.rukh.model.News;
import org.example.rukh.model.User;
import org.example.rukh.repository.CommentRepository;
import org.example.rukh.repository.NewsRepository;
import org.example.rukh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;
    public List<Comment> getCommentsById(int id) {
        return commentRepository.getCommentsById(id);
    }
    public String validateCommentData(int id, String text, int parent_comment_id, UserDetails userDetails){
        try{
            User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->  new IllegalArgumentException("User not found"));
            News news = newsRepository.getNewsById(id);
            if (text.isEmpty()) {
                throw new Exception("Пустой комментарий");
            }
            Comment comment = new Comment();
            comment.setContent(text);
            comment.setUser(user);
            comment.setNews(news);
            comment.setParent_comment_id(parent_comment_id);
            comment.setDate(new Date());
            commentRepository.save(comment);
            return null;
        }
        catch (Exception e){
            return "Ошибка при создании: "+e.getMessage();
        }
    }
}
