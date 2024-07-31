package org.example.rukh.controller;


import org.example.rukh.service.EmailService;
import org.example.rukh.service.UserService;
import org.example.rukh.model.User;
import org.example.rukh.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        String confirmPassword = payload.get("confirmPassword");
        String error = userService.validateUserData(email, password, confirmPassword);
        if (error == null) {
            String token = jwtUtil.generateToken(email);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        try {
            String email = payload.get("email");
            String password = payload.get("password");
            if(userService.authenticate(email, password)){
                String token = jwtUtil.generateToken(email);
                return ResponseEntity.ok(Map.of("token", token));
            }else return ResponseEntity.status(401).body(Collections.singletonMap("error","Неверные учетные данные"));
        }catch (Exception e){
            return ResponseEntity.status(401).body(Collections.singletonMap("error","Неверные учетные данные"));
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String username = userDetails.getUsername();
        User user = userService.getUser(username);
        if (user != null) {
            user.setAvatar("/uploads/" + user.getAvatar());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        try {
            emailService.sendSimpleEmail(email);
            return ResponseEntity.ok("Сообщение отправлено 1");
        } catch (Exception e) {
            return ResponseEntity.ok("Сообщение отправлено 2");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return ResponseEntity.ok("Вы успешно вышли из системы");
    }

}
