package com.example.info2020.Final_Project.services;

import com.example.info2020.Final_Project.controller.request.CreateComment;
import com.example.info2020.Final_Project.controller.request.UpdateComment;
import com.example.info2020.Final_Project.controller.response.CommentDto;

import java.util.List;

public interface CommentService  {
    String createComment(CreateComment body);

    List<CommentDto> getAllComments(Long postId);

    List<CommentDto> getComments(Long postId, int limit);

    String updateComment(Long commentId, UpdateComment body);

    String deleteComment(Long commentId);
}
