package com.nosql.dl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPost {

    @Id
    public String id;

    public String content;
    public LocalDateTime timestamp;
    public List<String> keywords;
    @DBRef
    public List<Comment> comments;
}
