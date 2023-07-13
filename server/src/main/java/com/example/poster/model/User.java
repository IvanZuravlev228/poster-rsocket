package com.example.poster.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String nick;
    private String name;
    private List<String> postIds;
}