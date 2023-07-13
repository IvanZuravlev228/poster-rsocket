package com.example.poster.controller;

import java.util.ArrayList;
import com.example.poster.dto.post.PostRequestDto;
import com.example.poster.model.Post;
import com.example.poster.model.User;
import com.example.poster.service.PostService;
import com.example.poster.service.UserService;
import com.example.poster.service.mapper.PostMapper;
import com.example.poster.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final PostService postService;
    private final UserMapper userMapper;
    private final PostMapper postMapper;


//    @PostMapping
    @MessageMapping("createUser")
    public Mono<Void> createUser(User user) {
        System.out.println("user: " + user);
//        user.setPostIds(new ArrayList<>());
        userService.createUser(user);
        return Mono.empty();
    }

//    @GetMapping("/{id}")
    @MessageMapping("getUserById")
    public Mono<User> getUserById(String id) {
        System.out.println("getUserById: id=" + id);
        return userService.getUserById(id);
    }

//    @PostMapping("/{userId}/posts")
    @MessageMapping("addPostToUser")
    public Mono<Void> addPostToUser(Post post) {
        System.out.println("post: " + post);
        Mono<User> userMono = getUserById(post.getUserId());
        Mono<Post> postMono = postService.createPost(post);

        Mono.zip(userMono, postMono)
                .flatMap(tuple -> {
                    User user = tuple.getT1();
                    Post createdPost = tuple.getT2();

                    // Добавляем идентификатор поста в поле postIds у пользователя
                    user.getPostIds().add(createdPost.getId());

                    // Сохраняем обновленного пользователя
                    userService.createUser(user);
                    return Mono.empty();
                });
        return Mono.empty();
    }
}


