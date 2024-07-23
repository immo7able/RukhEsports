package org.example.rukh.controller;

import org.example.rukh.service.AdminService;
import org.example.rukh.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/createNews")
    public ResponseEntity<?> createNews(@RequestParam String category,
                                        @RequestParam String content,
                                        @RequestParam String title,
                                        @RequestParam MultipartFile image,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        String error = adminService.validateNewsData(category, content, title, image);
        if (error == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
        }
    }
}
