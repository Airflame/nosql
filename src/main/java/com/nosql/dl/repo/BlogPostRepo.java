package com.nosql.dl.repo;

import com.nosql.dl.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogPostRepo extends MongoRepository<BlogPost, String> {
}
