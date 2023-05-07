package com.nosql.dl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Comment")
public class Comment {

    @Id
    public String id;

    public String author;
    public String content;
    public LocalDateTime timestamp;
    public boolean moderated;
}
