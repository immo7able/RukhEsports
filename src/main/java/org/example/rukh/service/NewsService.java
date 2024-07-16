package org.example.rukh.service;

import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.News;
import org.example.rukh.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream().map(this::convertToDTO).collect(Collectors.toList());

    }


    private NewsDTO convertToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setContent(news.getContent());
        newsDTO.setImage(news.getImage());
        newsDTO.setDate(news.getDate());
        newsDTO.setCategory(news.getCategory());
        return newsDTO;
    }
}
