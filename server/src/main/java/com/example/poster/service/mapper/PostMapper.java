package com.example.poster.service.mapper;

import java.time.LocalDateTime;
import com.example.poster.model.Post;
import com.example.poster.dto.post.PostRequestDto;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toModel(PostRequestDto dto) {
        Post post = new Post();
        post.setHead(dto.getHead());
        post.setText(dto.getText());
        post.setUserId(dto.getUserId());
        post.setTime(LocalDateTime.now());
        return post;
    }
}
