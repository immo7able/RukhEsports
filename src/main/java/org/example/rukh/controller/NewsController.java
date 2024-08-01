package org.example.rukh.controller;

import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.Likes;
import org.example.rukh.service.LikeService;
import org.example.rukh.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/{discipline}")
    public ResponseEntity<List<NewsDTO>> getNews(@PathVariable(value="discipline") String discipline) {
        List<NewsDTO> News = newsService.getNewsByDiscipline(discipline);
        return ResponseEntity.ok(News);
    }
    @GetMapping("/{discipline}/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable(value="discipline") String discipline, @PathVariable(value = "id") int id) {
        NewsDTO News = newsService.getNewsById(discipline, id);
        return ResponseEntity.ok(News);
    }
    @GetMapping("/{discipline}/{id}/likes")
    public ResponseEntity<Boolean> getLikesByNews(@PathVariable(value="discipline") String discipline, @PathVariable(value = "id") int id, @AuthenticationPrincipal UserDetails userDetails) {
        boolean likes = likeService.getLikesByNews(id, userDetails);
        return ResponseEntity.ok(likes);
    }
    @GetMapping("/")
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> News = newsService.getAllNews();
        return ResponseEntity.ok(News);
    }
    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeNews(@AuthenticationPrincipal UserDetails userDetails, @PathVariable int id, @RequestBody Map<String, String> payload) {
            String error = likeService.likeNews(id, Boolean.parseBoolean(payload.get("liked")), userDetails);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
    }
}

