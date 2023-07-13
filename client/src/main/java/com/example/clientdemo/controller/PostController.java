package com.example.clientdemo.controller;

import java.util.ArrayList;
import com.example.clientdemo.model.Post;
import com.example.clientdemo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final RSocketRequester requester;

    @PostMapping("/addPost")
    Mono<Void> addUser(@RequestBody Post post) {
        System.out.println("post: " + post);
        return this.requester
                .route("createPost")
                .data(post)
                .send();
    }

    @GetMapping
    public Mono<Post> getByOwner(@RequestParam String userId) {
        System.out.println("get Post by owner id: " + userId);
        return this.requester
                .route("getByOwner")
                .data(userId)
                .retrieveMono(Post.class);
    }
}
