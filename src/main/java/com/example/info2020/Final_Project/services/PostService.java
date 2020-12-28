package com.example.info2020.Final_Project.services;

import com.example.info2020.Final_Project.controller.request.CreatePost;
import com.example.info2020.Final_Project.controller.request.UpdatePost;
import com.example.info2020.Final_Project.controller.response.PostDto;

import java.util.List;

public interface PostService {

    String createPost(CreatePost body);

    List<PostDto> getPots();

    List<PostDto> getPotsByContainKeyWord(String keYWord);

    List<PostDto> getPotsIsNotPublished(boolean isPublished);

    List<PostDto> getPotsFilterBy(String keYWord, boolean notPublished);

    String updatePost(Long postId, UpdatePost body);

    String deletePost(Long postId);
}
