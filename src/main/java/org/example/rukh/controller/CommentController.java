package org.example.rukh.controller;

import org.example.rukh.model.Comment;
import org.example.rukh.model.DTO.CommentDTO;
import org.example.rukh.model.DTO.NewsDTO;
import org.example.rukh.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable(value="id") int id) {
        List<CommentDTO> comments = commentService.getCommentsById(id);
        return ResponseEntity.ok(comments);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> postComment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable(value="id") int id, @RequestBody Comment comment) {
        String error = commentService.validateCommentData(id, comment.getContent(), comment.getParent_comment_id(), userDetails);
        if (error == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetails userDetails, @PathVariable(value = "id") int id){
        String error = commentService.deleteComment(id, userDetails);
        if (error == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", error));
        }
    }
}
