package org.example.rukh.controller;

import org.example.rukh.model.User;
import org.example.rukh.model.DTO.UserProfileDTO;
import org.example.rukh.service.ProfileService;
import org.example.rukh.service.UserService;
import org.example.rukh.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @PutMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@RequestBody UserProfileDTO userProfileDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String error = profileService.updateUserEmail(userDetails, userProfileDTO.getEmail());
        if(error==null) {
            String token = jwtUtil.generateToken(userProfileDTO.getEmail());
            return ResponseEntity.ok().body(Map.of("token", token));
        }else return ResponseEntity.badRequest().body(Collections.singletonMap("error", error));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UserProfileDTO userProfileDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String error = profileService.updateUserPassword(userDetails, userProfileDTO.getPassword());
        if(error==null) {
            return ResponseEntity.ok("Пароль успешно изменен");
        }else return ResponseEntity.badRequest().body(Collections.singletonMap("error", error));
    }

    @PutMapping("/updateNickname")
    public ResponseEntity<?> updateNickname(@RequestBody UserProfileDTO userProfileDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String error = profileService.updateUserNickname(userDetails, userProfileDTO.getNickname());
        if(error==null) {
            return ResponseEntity.ok("Nickname updated successfully");
        }else return ResponseEntity.badRequest().body(Collections.singletonMap("error", error));
    }
    @PutMapping("/updateProfileImage")
    public ResponseEntity<?> updateProfileImage(@RequestParam MultipartFile avatar, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String error = profileService.updateUserProfileImage(userDetails, avatar );
        if(error==null) {
            User user = userService.getUser(userDetails.getUsername());
            return ResponseEntity.ok().body(Map.of("avatar","/uploads/"+user.getAvatar()));
        }else return ResponseEntity.badRequest().body(Collections.singletonMap("error", error));
    }
    /*@DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable("id") int id) {
        if (userDetails==null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not your comment");
        } else {
            String error = adminService.deleteComment(id);
            if (error == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
            }
        }
    }*/

}
