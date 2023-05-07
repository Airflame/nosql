package com.nosql.bl;

import com.nosql.dl.model.BlogPost;
import com.nosql.dl.model.Comment;
import com.nosql.dl.repo.BlogPostRepo;
import com.nosql.dl.repo.CommentRepo;
import com.nosql.sl.request.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BlogPostRepo blogPostRepo;

    private final CommentRepo commentRepo;

    public Comment addCommentToPost(AddCommentRequest addCommentRequest, String blogPostId) {
        BlogPost blogPost = blogPostRepo.findById(blogPostId).orElseThrow();
        Comment comment = commentRepo.save(Comment.builder().content(addCommentRequest.getContent())
                .author(addCommentRequest.getAuthor())
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
}
