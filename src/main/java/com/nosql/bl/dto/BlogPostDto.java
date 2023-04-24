package com.nosql.bl.dto;

import com.nosql.dl.model.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostDto {

    public String id;
    public String content;
    public LocalDateTime timestamp;
    public List<String> keywords;
    public Long likes;

    public static BlogPostDto mapFromBlogPost(BlogPost blogPost) {
        return BlogPostDto.builder().content(blogPost.getContent()).keywords(blogPost.getKeywords())
                .timestamp(blogPost.getTimestamp()).id(blogPost.getId()).likes(blogPost.getLikes()).build();
    }
}
