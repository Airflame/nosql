package com.nosql.sl;

import com.nosql.bl.BlogPostService;
import com.nosql.bl.CommentService;
import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.Comment;
import com.nosql.sl.request.AddBlogPostRequest;
import com.nosql.sl.request.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CommentEndpoint {

    private final CommentService commentService;

    @PostMapping("/api/blog-post/{blogPostId}/comment")
    @ResponseBody
    public Comment addComment(@PathVariable String blogPostId, @RequestBody AddCommentRequest addCommentRequest) {
        return commentService.addCommentToPost(addCommentRequest, blogPostId);
    }
}
