package org.example.rukh.service;

import org.example.rukh.model.User;
import org.example.rukh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("^[a-zA-Z0-9.@_]+$");


    public String validateUserData(String email, String password, String confirmPassword) {
        try{
            if (!password.equals(confirmPassword)) {
                throw new Exception("Пароли не совпадают");
            }
            if (userRepository.existsByEmail(email)) {
                throw new Exception("Email уже используется");
            }
            if (!Pattern.matches(EMAIL_PATTERN, email)) {
                throw new Exception("Неверный формат почты");
            }
            if(password.length()<6){
                throw new Exception("Пароль меньше 6 символов");
            }
            if(!password.matches(".*\\d.*")) {
                throw new Exception("В пароле должна быть 1 цифра");
            }
            if(!password.matches(".*[a-zA-Z].*")) {
                throw new Exception("В пароле должна быть 1 буква");
            }
            if(!password.matches(".*[A-Z].*")) {
                throw new Exception("В пароле должна быть 1 заглавная буква");
            }
            if (!isAllowedCharacters(email)) {
                throw new Exception("Почта содержит недопустимые символы");
            }
            if (!isAllowedCharacters(password)) {
                throw new Exception("Пароль содержит недопустимые символы");
            }
            User user = new User();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole("user");
            userRepository.save(user);
            return null;
        }
        catch (Exception e){
            return "Ошибка при регистрации: "+e.getMessage();
        }
    }
    private boolean isAllowedCharacters(String input) {
        return ALLOWED_CHARACTERS_PATTERN.matcher(input).matches();
    }

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() ->  new IllegalArgumentException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
    }
}
