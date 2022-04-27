package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.dtos.PostDTO;
import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.LibraryRepository;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final StringGeneratorService stringGeneratorService;
    private final LibraryRepository libraryRepository;

    public PostService(PostRepository postRepository, StringGeneratorService stringGeneratorService, @Lazy LibraryRepository libraryRepository) {
        this.postRepository = postRepository;
        this.stringGeneratorService = stringGeneratorService;
        this.libraryRepository = libraryRepository;
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

    public PostModelResponse findBySlug(String slug) {
        Post post = this.postRepository.findBySlug(slug);
        return new PostModelResponse(post);
    }

    public List<PostModelResponse> findByUsername(String username) {
        return postRepository.findByCreatedByUserUsername(username).stream().map(PostModelResponse::new).toList();
    }

    public PostModelResponse save(Post post) {
        post.setSlug(stringGeneratorService.generateSlug(post.getTitle()));
        Post savedPost = postRepository.save(post);
        return new PostModelResponse(savedPost);
    }

    private Post getWithLibraryPopulated(PostDTO postDTO) {
        Post post = postDTO.getPostModel();
        post.setParentLibrary(this.libraryRepository.findBySlug(postDTO.getLibrary()));
        return post;
    }

    public PostModelResponse save(PostDTO newPostDTO) {
        Post post = this.getWithLibraryPopulated(newPostDTO);
        return this.save(post);
    }

    public PostModelResponse update(PostDTO updatePostDTO) {
        Post currentPost = this.postRepository.findBySlug(updatePostDTO.getPost());
        Post newPost = this.getWithLibraryPopulated(updatePostDTO);

        if(!currentPost.getTitle().equalsIgnoreCase(newPost.getTitle())) {
            // title changed
            currentPost.setTitle(updatePostDTO.getTitle());
            currentPost.setSlug(stringGeneratorService.generateSlug(updatePostDTO.getTitle()));
        }
        currentPost.setText(newPost.getText());
        currentPost.setParentLibrary(newPost.getParentLibrary());

        Post savedPost = postRepository.save(currentPost);
        return new PostModelResponse(savedPost);
    }
}
