package com.nosql.sl;

import com.mongodb.lang.Nullable;
import com.nosql.bl.BlogPostService;
import com.nosql.bl.dto.BlogPostDto;
import com.nosql.dl.model.BlogPost;
import com.nosql.sl.request.AddBlogPostRequest;
import com.nosql.sl.request.UpdateBlogPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public BlogPostDto addBlogPost(@RequestBody AddBlogPostRequest addBlogPostRequest) {
        return blogPostService.addBlogPost(addBlogPostRequest);
    }

    @DeleteMapping("/{blogPostId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public void deleteBlogPost(@PathVariable(name = "blogPostId") String blogPostId) {
        blogPostService.deleteBlogPost(blogPostId);
    }

    @PutMapping("/{blogPostId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public BlogPostDto updateBlogPost(@RequestBody UpdateBlogPostRequest updateBlogPostRequest, @PathVariable(name = "blogPostId") String blogPostId) {
        return blogPostService.updateBlogPost(updateBlogPostRequest, blogPostId);
    }

    @PutMapping("/{blogPostId}/likeUp")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority({'ADMIN', 'GUEST'})")
    public BlogPostDto likeBlogPost(@PathVariable(name = "blogPostId") String blogPostId) {
        return blogPostService.likeBlogPost(blogPostId);
    }

    @GetMapping
    @ResponseBody
    public List<BlogPost> getBlogPosts(
            @Nullable @RequestParam(name = "from") String from,
            @Nullable @RequestParam(name = "to") String to
    ) {
        if (from != null && to != null) return blogPostService.findBlogPostByTimestampBetween(from, to);
        else if (from != null) return blogPostService.findBlogPostByTimestampFrom(from);
        else if (to != null) return blogPostService.findBlogPostByTimestampTo(to);
        else return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{blogPostId}")
    @ResponseBody
    public BlogPost getBlogPostById(@PathVariable(name = "blogPostId") String blogPostId) {
        return blogPostService.getBlogPostById(blogPostId);
    }

}
