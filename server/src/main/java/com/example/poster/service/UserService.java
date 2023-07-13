package com.example.poster.service;

import com.example.poster.model.Post;
import com.example.poster.model.User;
import com.example.poster.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }
    
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> addPostToUser(String userId, String postId) {
        return userRepository.findById(userId)
                .flatMap(user -> {
                    user.getPostIds().add(postId);
                    return userRepository.save(user);
                });
    }
}
