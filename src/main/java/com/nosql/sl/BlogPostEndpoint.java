package com.nosql.sl;

import com.nosql.bl.BlogPostService;
import com.nosql.bl.dto.BlogPostDto;
import com.nosql.sl.request.AddBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BlogPostEndpoint {

    private final BlogPostService blogPostService;

    @PostMapping("/api/blog-post")
    @ResponseBody
    public BlogPostDto addBlogPost(@RequestBody AddBlogPostRequest addBlogPostRequest) {
        return blogPostService.addBlogPost(addBlogPostRequest);
    }
}
