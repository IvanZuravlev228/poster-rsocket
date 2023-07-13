package com.example.clientdemo.model;

import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    private String id;
    private String nick;
    private String name;
    private List<String> postIds;
}
