package org.example.rukh.service;

import org.example.rukh.model.User;
import org.example.rukh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public void updateUserEmail(String email, String newEmail) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public void updateUserPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public void updateUserNickname(String email, String newNickname) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));
        user.setNickname(newNickname);
        userRepository.save(user);
    }
}
