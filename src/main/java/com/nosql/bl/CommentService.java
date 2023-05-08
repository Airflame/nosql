package com.nosql.bl;

import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.BlogPost;
import com.nosql.dl.model.Comment;
import com.nosql.dl.repo.BlogPostRepo;
import com.nosql.dl.repo.CommentRepo;
import com.nosql.sl.request.AddCommentRequest;
import com.nosql.sl.request.UpdateBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BlogPostRepo blogPostRepo;

    private final CommentRepo commentRepo;

    public Comment addCommentToPost(AddCommentRequest addCommentRequest, String blogPostId, Authentication authentication) {
        BlogPost blogPost = blogPostRepo.findById(blogPostId).orElseThrow();
        Comment comment = commentRepo.save(Comment.builder().content(addCommentRequest.getContent())
                .author(authentication.getName())
                .timestamp(LocalDateTime.now())
                .moderated(false).build());
        List<Comment> comments = blogPost.getComments();
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        blogPost.setComments(comments);
        blogPostRepo.save(blogPost);
        return comment;
    }

    public List<Comment> getAllCommentsForPost(String blogPostId) {
        BlogPost blogPost = blogPostRepo.findById(blogPostId).orElseThrow();
        return blogPost.getComments();
    }

    public void moderateComment(String commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow();
        comment = commentRepo.save(Comment.builder()
                .id(comment.getId())
                .author(comment.getAuthor())
                .content(comment.getContent())
                .timestamp(comment.getTimestamp())
                .moderated(true)
                .build());
    }
}
