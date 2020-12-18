package com.example.info2020.Final_Project.controller;

import com.example.info2020.Final_Project.controller.request.CreatePost;
import com.example.info2020.Final_Project.controller.request.UpdatePost;
import com.example.info2020.Final_Project.controller.response.PostDto;
import com.example.info2020.Final_Project.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<String> createPost(@RequestBody CreatePost body){
        return ResponseEntity.ok(postService.createPost(body));
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDto>> getPosts(
            @RequestParam(required = false, value = "keyWord")String keYWord,
            @RequestParam(required = false, value = "notPublished")boolean notPublished){
        if(StringUtils.hasText(keYWord)&& notPublished){
            return ResponseEntity.ok(postService.getPotsFilterBy(keYWord, true));
        }
        if (StringUtils.hasText(keYWord)){
            return ResponseEntity.ok(postService.getPotsByContainKeyWord(keYWord));
        }
        if(notPublished){
            return ResponseEntity.ok(postService.getPotsIsNotPublished(true));
        }

        return ResponseEntity.ok(postService.getPots());
    }

    @PutMapping(value = "/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable(value = "postId") Long postId, @RequestBody UpdatePost body){
        return ResponseEntity.ok(postService.updatePost(postId, body));

    }

    @DeleteMapping(value = "/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "postId")Long postId){
        return ResponseEntity.ok(postService.deletePost(postId));
    }

}
