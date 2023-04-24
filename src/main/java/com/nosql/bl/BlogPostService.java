package com.nosql.bl;

import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.BlogPost;
import com.nosql.dl.model.Comment;
import com.nosql.dl.repo.BlogPostRepo;
import com.nosql.dl.repo.CommentRepo;
import com.nosql.sl.request.AddBlogPostRequest;
import com.nosql.sl.request.UpdateBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogPostService {

    private final BlogPostRepo blogPostRepo;
    private final CommentRepo commentRepo;

    public BlogPostDto addBlogPost(AddBlogPostRequest addBlogPostRequest) {
        BlogPost blogPost = blogPostRepo.save(BlogPost.builder().content(addBlogPostRequest.getContent())
                .timestamp(LocalDateTime.now()).keywords(addBlogPostRequest.getKeywords()).likes(0L).build());
        return BlogPostDto.mapFromBlogPost(blogPost);
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepo.findAll();
    }

    public BlogPost getBlogPostById(String blogPostId) {
        Optional<BlogPost> blogPostOpt = blogPostRepo.findById(blogPostId);
        return blogPostOpt.get();
    }

    public void deleteBlogPost(String blogPostId) {
        BlogPost blogPost = blogPostRepo.findById(blogPostId).orElseThrow();
        if (!blogPost.getComments().isEmpty()) {
            for (Comment comment : blogPost.getComments()) {
                commentRepo.deleteById(comment.getId());
            }
        }
        blogPostRepo.deleteById(blogPostId);
    }

    public BlogPostDto updateBlogPost(UpdateBlogPostRequest updateBlogPostRequest, String blogPostId) {
        BlogPost blogPost = blogPostRepo.findById(blogPostId).orElseThrow();
        blogPost = blogPostRepo.save(BlogPost.builder()
                .id(blogPost.getId())
                .content(updateBlogPostRequest.getContent())
                .keywords(updateBlogPostRequest.getKeywords())
                .timestamp(blogPost.getTimestamp())
                .comments(blogPost.getComments())
                .likes(blogPost.getLikes())
                .build());
        return BlogPostDto.mapFromBlogPost(blogPost);
    }
}
