package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }
}
