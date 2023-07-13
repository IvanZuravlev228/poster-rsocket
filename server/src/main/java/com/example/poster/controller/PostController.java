package com.example.poster.controller;

import com.example.poster.model.Post;
import com.example.poster.dto.post.PostRequestDto;
import com.example.poster.service.mapper.PostMapper;
import com.example.poster.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PostController {
    private final PostService postService;
    private final PostMapper mapper;

//    @PostMapping
    @MessageMapping("createPost")
    public Mono<Void> createPost(Post post) {
        System.out.println("post: " + post);
        postService.createPost(post);
        return Mono.empty();
    }

//    @GetMapping
    @MessageMapping("getByOwner")
    public Mono<Post> getByOwner(String userId) {
        System.out.println("get Post by owner id: " + userId);
        Mono<Post> byOwner = postService.getByOwner(userId);
        System.out.println(byOwner);
        return byOwner;
    }
}
