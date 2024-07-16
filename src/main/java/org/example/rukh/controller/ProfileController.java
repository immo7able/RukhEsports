package org.example.rukh.controller;

import org.example.rukh.model.UserProfileDTO;
import org.example.rukh.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping("/updateEmail")
    public ResponseEntity<String> updateEmail(@RequestBody UserProfileDTO userProfileDTO, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        String username = userDetails.getUsername();
        profileService.updateUserEmail(username, userProfileDTO.getEmail());
        return ResponseEntity.ok("Email updated successfully");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UserProfileDTO userProfileDTO, Principal principal) {
        String username = principal.getName();
        profileService.updateUserPassword(username, userProfileDTO.getPassword());
        return ResponseEntity.ok("Password updated successfully");
    }

    /*@PutMapping("/updateNickname")
    public ResponseEntity<String> updateNickname(@RequestBody UserProfileDTO userProfileDTO, Principal principal) {
        String username = principal.getName();
        profileService.updateUserNickname(username, userProfileDTO.getNickname());
        return ResponseEntity.ok("Nickname updated successfully");
    }*/

}
