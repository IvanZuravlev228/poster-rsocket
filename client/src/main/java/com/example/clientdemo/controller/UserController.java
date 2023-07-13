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
@RequestMapping("/users")
public class UserController {
    private final RSocketRequester requester;

    @GetMapping
    public Mono<User> getUserById(@RequestParam String id) {
        System.out.println("get user by id: " + id);
        return this.requester
                .route("getUserById")
                .data(id)
                .retrieveMono(User.class);
    }

    @PostMapping("/addUser")
    Mono<Void> addUser(@RequestBody User user) {
        user.setPostIds(new ArrayList<>());
        System.out.println("user: " + user);
        return this.requester
                .route("createUser")
                .data(user)
                .send();
    }

    @PostMapping("/addPost")
    Mono<Void> addPostToUser(@RequestBody Post post) {
        System.out.println("post: " + post);
        return this.requester
                .route("addPostToUser")
                .data(post)
                .send();
    }

}
