package com.example.clientcomments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String comment;
    private Long userId;

    public Comment(String comment, Long userId) {
        this.comment = comment;
        this.userId = userId;
    }
}
