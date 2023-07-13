package com.example.poster.service;

import com.example.poster.model.Post;
import com.example.poster.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    
    public Mono<Post> createPost(Post post) {
        return postRepository.save(post);
    }

    public Mono<Post> getByOwner(String userId) {
        return postRepository.findByUserId(userId);
    }
}
