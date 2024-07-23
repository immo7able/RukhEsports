package org.example.rukh.controller;

import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{discipline}")
    public ResponseEntity<Map<String, List<NewsDTO>>> getNews(@PathVariable(value="discipline") String discipline) {
        List<NewsDTO> allNews = newsService.getAllNews();
        List<NewsDTO> filteredNews = allNews.stream()
                .filter(news -> news.getCategory().equalsIgnoreCase(discipline))
                .collect(Collectors.toList());

        Map<String, List<NewsDTO>> response = new HashMap<>();
        response.put(discipline, filteredNews);

        return ResponseEntity.ok(response);
    }
}

