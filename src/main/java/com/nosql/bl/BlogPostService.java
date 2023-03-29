package com.nosql.bl;

import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.BlogPost;
import com.nosql.dl.repo.BlogPostRepo;
import com.nosql.sl.request.AddBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepo blogPostRepo;

    public BlogPostDto addBlogPost(AddBlogPostRequest addBlogPostRequest) {
        BlogPost blogPost = blogPostRepo.save(BlogPost.builder().content(addBlogPostRequest.getContent())
                .timestamp(LocalDateTime.now()).keywords(addBlogPostRequest.getKeywords()).build());
        return BlogPostDto.mapFromBlogPost(blogPost);
    }
}
