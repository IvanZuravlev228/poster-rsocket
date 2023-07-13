package com.example.poster.dto.post;

import lombok.Data;

@Data
public class PostRequestDto {
    private String head;
    private String text;
    private String userId;
}
