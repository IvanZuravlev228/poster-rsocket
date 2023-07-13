package com.example.poster.service.mapper;

import com.example.poster.dto.user.UserRequestDto;
import com.example.poster.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setNick(dto.getNick());
        user.setPostIds(dto.getPostId());
        return user;
    }
}
