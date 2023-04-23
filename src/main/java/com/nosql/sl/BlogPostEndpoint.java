package com.nosql.sl;

import com.nosql.bl.BlogPostService;
import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.BlogPost;
import com.nosql.sl.request.AddBlogPostRequest;
import com.nosql.sl.request.UpdateBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/blog-post")
public class BlogPostEndpoint {

    private final BlogPostService blogPostService;

    @PostMapping
    @ResponseBody
    public BlogPostDto addBlogPost(@RequestBody AddBlogPostRequest addBlogPostRequest) {
        return blogPostService.addBlogPost(addBlogPostRequest);
    }

    @DeleteMapping("/{blogPostId}")
    public void deleteBlogPost(@PathVariable(name = "blogPostId") String blogPostId) {
        blogPostService.deleteBlogPost(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    @ResponseBody
    public BlogPostDto updateBlogPost(@RequestBody UpdateBlogPostRequest updateBlogPostRequest, @PathVariable(name = "blogPostId") String blogPostId) {
        return blogPostService.updateBlogPost(updateBlogPostRequest, blogPostId);
    }

    @GetMapping
    @ResponseBody
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{blogPostId}")
    @ResponseBody
    public BlogPost getBlogPostById(@PathVariable(name = "blogPostId") String blogPostId) {
        return blogPostService.getBlogPostById(blogPostId);
    }
}
