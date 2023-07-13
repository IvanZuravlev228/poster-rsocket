package com.example.poster.dto.post;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostResponseDto {
    private String id;
    private String head;
    private String text;
    private String userId;
    private LocalDateTime time;
}
