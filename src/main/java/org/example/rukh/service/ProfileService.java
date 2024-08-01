package org.example.rukh.service;

import org.example.rukh.model.User;
import org.example.rukh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${upload.path}")
    private String uploadPath;

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");
    private boolean isAllowedCharacters(String input) {
        return ALLOWED_CHARACTERS_PATTERN.matcher(input).matches();
    }

    public String updateUserEmail(UserDetails userDetails, String newEmail) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        try{
            if (userRepository.existsByEmail(newEmail)) {
                throw new Exception("Email уже используется");
            }
            if (!Pattern.matches(EMAIL_PATTERN, newEmail)) {
                throw new Exception("Неверный формат почты");
            }
            if (!isAllowedCharacters(newEmail)) {
                throw new Exception("Почта содержит недопустимые символы");
            }
            updateSecurityContext(userDetails, newEmail);
            user.setEmail(newEmail);
            userRepository.save(user);
            return null;
        }catch (Exception e){
            return "Ошибка: "+e.getMessage();
        }

    }

    public String updateUserPassword(UserDetails userDetails, String newPassword) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        try{
            if (!isAllowedCharacters(newPassword)) {
                throw new Exception("Пароль содержит недопустимые символы");
            }
            if(!newPassword.matches(".*\\d.*")) {
                throw new Exception("В пароле должна быть 1 цифра");
            }
            if(!newPassword.matches(".*[a-z].*")) {
                throw new Exception("В пароле должна быть 1 строчная буква");
            }
            if(!newPassword.matches(".*[A-Z].*")) {
                throw new Exception("В пароле должна быть 1 заглавная буква");
            }
            if(newPassword.length()<6){
                throw new Exception("Пароль меньше 6 символов");
            }
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return null;
        }catch (Exception e){
            return "Ошибка: "+e.getMessage();
        }
    }

    public String updateUserNickname(UserDetails userDetails, String newNickname) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        try{
            if (!isAllowedCharacters(newNickname)) {
                throw new Exception("Никнейм содержит недопустимые символы");
            }
            if(newNickname.length()<3){
                throw new Exception("Никнейм меньше 3 символов");
            }
            user.setNickname(newNickname);
            userRepository.save(user);
            return null;
        }catch (Exception e){
            return "Ошибка: "+e.getMessage();
        }
    }
    public String updateUserProfileImage(UserDetails userDetails, MultipartFile avatar) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        try{
            if ((avatar == null) || avatar.getOriginalFilename().isEmpty()) {
                throw new Exception("Аватар пуст");
            }
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String UUIDAvatar = UUID.randomUUID().toString();
            String resultAvatarName = UUIDAvatar+"."+avatar.getOriginalFilename();
            avatar.transferTo(new File(uploadPath+"/"+resultAvatarName));
            user.setAvatar(resultAvatarName);
            userRepository.save(user);
            return null;
        }catch (Exception e){
            return "Ошибка: "+e.getMessage();
        }
    }
    private void updateSecurityContext(UserDetails userDetails, String newEmail) {
        UserDetails updatedUserDetails = org.springframework.security.core.userdetails.User.withUsername(newEmail)
                .password(userDetails.getPassword())
                .authorities(userDetails.getAuthorities())
                .build();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                updatedUserDetails, userDetails.getPassword(), updatedUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
