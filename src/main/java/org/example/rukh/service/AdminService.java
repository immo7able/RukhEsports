package org.example.rukh.service;

import jakarta.transaction.Transactional;
import org.example.rukh.model.News;
import org.example.rukh.model.Player;
import org.example.rukh.model.Team;
import org.example.rukh.repository.CommentRepository;
import org.example.rukh.repository.NewsRepository;
import org.example.rukh.repository.PlayerRepository;
import org.example.rukh.repository.TeamRepository;
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
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Value("${upload.path}")
    private String uploadPath;
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");
    public String validateNewsData(String category, String content, String title, MultipartFile image){
        try{
            if (!category.equalsIgnoreCase("pubg")&&!category.equalsIgnoreCase("mob")&&!category.equalsIgnoreCase("hok")) {
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
            news.setCategory(category.toLowerCase());
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
    public String updateNewsData(int id, String category, String content, String title, MultipartFile image){
        try{
            if(!newsRepository.existsById(id)){
                throw new Exception("Неправильный id");
            }
            if (!category.equalsIgnoreCase("pubg")&&!category.equalsIgnoreCase("mob")&&!category.equalsIgnoreCase("hok")) {
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
            News news = newsRepository.getNewsById(id);
            news.setCategory(category.toLowerCase());
            news.setContent(content);
            news.setTitle(title);
            news.setImage(resultImageName);
            news.setDate(new Date());
            newsRepository.save(news);
            return null;
        }
        catch (Exception e){
            return "Ошибка при обновлении: "+e.getMessage();
        }
    }
    @Transactional
    public String deleteNews(int id){
        try{
            if(!newsRepository.existsById(id)){
                throw new Exception("Неправильный id");
            }
            News news = newsRepository.getNewsById(id);
            if(commentRepository.existsByNews(news))
                commentRepository.deleteAllByNews(news);
            newsRepository.delete(news);
            return null;
        }
        catch (Exception e){
            return "Ошибка при обновлении: "+e.getMessage();
        }
    }
    public String validateTeamData(String category, String content, String name, MultipartFile image){
        try{
            if (!category.equalsIgnoreCase("pubg")&&!category.equalsIgnoreCase("mob")&&!category.equalsIgnoreCase("hok")) {
                throw new Exception("Неверная категория");
            }
            if (content.isEmpty()) {
                throw new Exception("Пустая новость");
            }
            if (name.isEmpty()) {
                throw new Exception("Пустое название");
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
            Team team = new Team();
            team.setDiscipline(category.toLowerCase());
            team.setContent(content);
            team.setName(name);
            team.setImg(resultImageName);
            teamRepository.save(team);
            return null;
        }
        catch (Exception e){
            return "Ошибка при создании: "+e.getMessage();
        }
    }
    public String updateTeamData(int id, String category, String content, String name, MultipartFile image){
        try{
            if(!teamRepository.existsById(id)){
                throw new Exception("Неправильный id");
            }
            if (!category.equalsIgnoreCase("pubg")&&!category.equalsIgnoreCase("mob")&&!category.equalsIgnoreCase("hok")) {
                throw new Exception("Неверная категория");
            }
            if (content.isEmpty()) {
                throw new Exception("Пустая новость");
            }
            if (name.isEmpty()) {
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
            Team team = teamRepository.getTeamById(id);
            team.setDiscipline(category.toLowerCase());
            team.setContent(content);
            team.setName(name);
            team.setImg(resultImageName);
            teamRepository.save(team);
            return null;
        }
        catch (Exception e){
            return "Ошибка при обновлении: "+e.getMessage();
        }
    }
    @Transactional
    public String deleteTeam(int id){
        try{
            if(!teamRepository.existsById(id)){
                throw new Exception("Неправильный id");
            }
            Team team = teamRepository.getTeamById(id);
            if(playerRepository.existsByTeam(team))
                playerRepository.deleteAllByTeam(team);
            teamRepository.delete(team);
            return null;
        }
        catch (Exception e){
            return "Ошибка при обновлении: "+e.getMessage();
        }
    }
    public String validatePlayerData(String nickname, String name, String content, MultipartFile image, int team_id, String socialMediaLinks){
        try{
            if (!teamRepository.existsById(team_id)) {
                throw new Exception("Неверная команда");
            }
            if (content.isEmpty()) {
                throw new Exception("Пустое описание");
            }
            if (name.isEmpty()) {
                throw new Exception("Пустое имя");
            }
            if (nickname.isEmpty()) {
                throw new Exception("Пустой ник");
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
            Player player = new Player();
            player.setNickname(nickname);
            player.setName(name);
            player.setContent(content);
            player.setImg(resultImageName);
            player.setTeam(teamRepository.getTeamById(team_id));
            player.setSocialMediaLinks(socialMediaLinks);
            playerRepository.save(player);
            return null;
        }
        catch (Exception e){
            return "Ошибка при создании: "+e.getMessage();
        }
    }
}
