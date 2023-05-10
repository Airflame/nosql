package com.nosql.dl.repo;

import com.nosql.dl.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogPostRepo extends MongoRepository<BlogPost, String> {
    List<BlogPost> findBlogPostByTimestampBetween(LocalDateTime from, LocalDateTime to);
    List<BlogPost> findBlogPostByTimestampAfter(LocalDateTime from);
    List<BlogPost> findBlogPostByTimestampBefore(LocalDateTime to);
    List<BlogPost> findBlogPostByLikesGreaterThanEqual(Long gte);
    List<BlogPost> findBlogPostByLikesLessThanEqual(Long lte);
    List<BlogPost> findBlogPostByLikesBetween(Long lte, Long gte);
    List<BlogPost> findBlogPostByKeywordsContains(List<String> keywords);
    List<BlogPost> findBlogPostByCommentsIsNotNull();
    List<BlogPost> findBlogPostByContentContaining(String text);
}
