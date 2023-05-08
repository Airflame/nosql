package com.nosql.sl;

import com.nosql.bl.CommentService;
import com.nosql.dl.model.Comment;
import com.nosql.sl.request.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentEndpoint {

    private final CommentService commentService;

    @PostMapping("/api/blog-post/{blogPostId}/comment")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'GUEST'})")
    public Comment addComment(@PathVariable("blogPostId") String blogPostId, @RequestBody AddCommentRequest addCommentRequest, Authentication authentication) {
        return commentService.addCommentToPost(addCommentRequest, blogPostId, authentication);
    }

    @GetMapping("/api/blog-post/{blogPostId}/comments")
    @ResponseBody
    public List<Comment> getAllCommentsForPost(@PathVariable("blogPostId") String blogPostId) {
        return commentService.getAllCommentsForPost(blogPostId);
    }
}
