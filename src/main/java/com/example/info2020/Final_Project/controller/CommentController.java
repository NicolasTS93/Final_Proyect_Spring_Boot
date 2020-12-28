package com.example.info2020.Final_Project.controller;

import com.example.info2020.Final_Project.controller.request.CreateComment;
import com.example.info2020.Final_Project.controller.request.UpdateComment;
import com.example.info2020.Final_Project.controller.response.CommentDto;
import com.example.info2020.Final_Project.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) { this.commentService =  commentService;}

    @PostMapping(value = "/comment")
    public ResponseEntity<String> createComment(@RequestBody CreateComment body){
        return ResponseEntity.ok(commentService.createComment(body));
    }

    @GetMapping(value = "/comment")
    public ResponseEntity<List<CommentDto>> getComments (
            @RequestParam(value = "postId") Long postId,
            @RequestParam(value = "limit", defaultValue = "0") int limit){
        if (limit > 0){
            return ResponseEntity.ok(commentService.getComments(postId, limit));
        }

        return ResponseEntity.ok(commentService.getAllComments(postId));
    }

    @PutMapping(value = "/comment/{commentId}")
    public ResponseEntity<String> updateComment (@PathVariable(value = "commentId") Long commentId, @RequestBody UpdateComment body){
        return ResponseEntity.ok(commentService.updateComment(commentId, body));

    }

    @DeleteMapping(value = "/comment/{commentId}")
    public ResponseEntity<String> deleteComment (@PathVariable(value = "commentId") Long commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }






}
