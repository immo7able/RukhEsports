package org.example.rukh.service;

import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.model.News;
import org.example.rukh.repository.LikeRepository;
import org.example.rukh.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<NewsDTO> getNewsByDiscipline(String discipline) {
        discipline = discipline.toLowerCase();
        List<News> newsList = newsRepository.getNewsByDiscipline(discipline);
        return newsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public NewsDTO getNewsById(String discipline, int id) {
        discipline=discipline.toLowerCase();
        News news = newsRepository.getNewsById(discipline, id);
        return convertToDTO(news);
    }




    private NewsDTO convertToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(news.getId());
        newsDTO.setTitle(news.getTitle());
        newsDTO.setContent(news.getContent());
        newsDTO.setImage("/uploads/"+news.getImage());
        newsDTO.setDate(news.getDate());
        newsDTO.setCategory(news.getCategory());
        newsDTO.setLikeCount(likeRepository.countLikesByNews(news));
        return newsDTO;
    }
}
