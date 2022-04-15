package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CommentModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
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

    public List<PostModelResponse> findAll() {
        return postRepository.findAll().stream().map(PostModelResponse::new).toList();
    }

    public List<PostModelResponse> findLatest(int limit) {
        return postRepository.findLatest(limit).stream().map(PostModelResponse::new).toList();
    }

    public List<PostModelResponse> findByParentLibrarySlug(String librarySlug) {
        return postRepository.findByParentLibrarySlug(librarySlug).stream().map(PostModelResponse::new).toList();
    }

    public List<PostModelResponse> findBySlug(String slug) {
        return postRepository.findBySlug(slug).stream().map(PostModelResponse::new).toList();
    }

    public PostModelResponse save(Post post) {
        post.setSlug(stringGeneratorService.generateSlug(post.getTitle()));
        Post savedPost = postRepository.save(post);
        return new PostModelResponse(savedPost);
    }
}
