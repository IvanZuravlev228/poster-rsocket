package com.example.poster.dto.user;

import java.util.List;
import com.example.poster.model.Post;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserRequestDto {
    private String nick;
    private Integer age;
    private String name;
    private List<String> postId;
}
