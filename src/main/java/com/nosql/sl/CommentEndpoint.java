package com.nosql.sl;

import com.nosql.bl.CommentService;
import com.nosql.dl.model.Comment;
import com.nosql.sl.request.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentEndpoint {

    private final CommentService commentService;

    @PostMapping("/api/blog-post/{blogPostId}/comment")
    @ResponseBody
    public Comment addComment(@PathVariable("blogPostId") String blogPostId, @RequestBody AddCommentRequest addCommentRequest) {
        return commentService.addCommentToPost(addCommentRequest, blogPostId);
    }

    @GetMapping("/api/blog-post/{blogPostId}/comments")
    @ResponseBody
    public List<Comment> getAllCommentsForPost(@PathVariable("blogPostId") String blogPostId) {
        return commentService.getAllCommentsForPost(blogPostId);
    }
}
