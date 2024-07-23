package org.example.rukh.controller;

import org.example.rukh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/createNews")
    public ResponseEntity<?> createNews(@RequestParam("content") String content,
                                        @RequestParam("discipline") String discipline,
                                        @RequestParam("title") String title,
                                        @RequestParam("image") MultipartFile image,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(role -> role.equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not admin");
        } else {
            String error = adminService.validateNewsData(discipline, content, title, image);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }
}
