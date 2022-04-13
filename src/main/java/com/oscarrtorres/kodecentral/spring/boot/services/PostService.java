package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final StringGeneratorService stringGeneratorService;

    public PostService(PostRepository postRepository, StringGeneratorService stringGeneratorService) {
        this.postRepository = postRepository;
        this.stringGeneratorService = stringGeneratorService;
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream().map(PostResponse::new).toList();
    }

    public PostResponse save(Post post) {
        post.setSlug(stringGeneratorService.generateSlug(post.getTitle()));
        Post savedPost = postRepository.save(post);
        return new PostResponse(savedPost);
    }

    public List<Post> findBySlug(String slug) {
        return postRepository.findBySlug(slug);
    }
}
