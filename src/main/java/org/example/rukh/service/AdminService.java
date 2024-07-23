package org.example.rukh.service;

import org.example.rukh.model.News;
import org.example.rukh.model.User;
import org.example.rukh.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class AdminService {
    @Autowired
    private NewsRepository newsRepository;
    @Value("${upload.path}")
    private String uploadPath;
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");
    public String validateNewsData(String category, String content, String title, MultipartFile image){
        try{
            if (!category.equals("pubg")&&!category.equals("mlbb")&&!category.equals("hok")) {
                throw new Exception("Неверная категория");
            }
            if (content.isEmpty()) {
                throw new Exception("Пустая новость");
            }
            if (title.isEmpty()) {
                throw new Exception("Пустой заголовок");
            }
            if ((image == null) || image.getOriginalFilename().isEmpty()) {
                throw new Exception("Картинка пустая");
            }
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String UUIDAvatar = UUID.randomUUID().toString();
            String resultImageName = UUIDAvatar+"."+image.getOriginalFilename();
            image.transferTo(new File(uploadPath+"/"+resultImageName));
            News news = new News();
            news.setCategory(category);
            news.setContent(content);
            news.setTitle(title);
            news.setImage(resultImageName);
            news.setDate(new Date());
            newsRepository.save(news);
            return null;
        }
        catch (Exception e){
            return "Ошибка при создании: "+e.getMessage();
        }
    }
}
