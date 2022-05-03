package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.dtos.PostDTO;
import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.LibraryRepository;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        List<PostModelResponse> responseList = postRepository.findByParentLibrarySlugOrderByLibraryIndex(librarySlug).stream().map(PostModelResponse::new).toList();
        for(PostModelResponse pmr: responseList) {
            pmr.calculatePreviousAndNextPost();
        }
        return responseList;
    }

    public PostModelResponse findBySlug(String slug) {
        Post post = this.postRepository.findBySlug(slug);
        PostModelResponse pmr = new PostModelResponse(post);
        pmr.calculatePreviousAndNextPost();
        return pmr;
    }

    public List<PostModelResponse> findByUsername(String username) {
        return postRepository.findByCreatedByUserUsernameOrderByLibraryIndex(username).stream().map(PostModelResponse::new).toList();
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

    @Transactional
    private void updatePostLibraryIndexOrder(Post savedPost) {
        // 1. get all posts in this library (ordered by library index)
        List<Post> posts = postRepository.findByParentLibrarySlugOrderByLibraryIndex(savedPost.getParentLibrary().getSlug());
        if(posts.size() <=1 ) {
            // no need to update
            return;
        }

        // find the post and remove it from the list
        int index = 0;
        for (int i = 0; i < posts.size(); i++) {
            if(posts.get(i).getSlug().equals(savedPost.getSlug())) {
                index = i;
                break;
            }
        }

        posts.remove(index);

        // add it back to the list in the index it wants to be in
        posts.add((int)savedPost.getLibraryIndex(), savedPost);

        for (int i = 0; i < posts.size(); i++) {
            Post p = posts.get(i);
            p.setLibraryIndex(i);
            this.postRepository.save(p);
        }
    }

    public PostModelResponse save(PostDTO newPostDTO) {
        Post post = this.getWithLibraryPopulated(newPostDTO);
        return this.save(post);
    }

    public PostModelResponse update(PostDTO updatePostDTO) {
        Post currentPost = this.postRepository.findBySlug(updatePostDTO.getPost());
        Post oldPost = currentPost.toBuilder().build();
        Post inPost = this.getWithLibraryPopulated(updatePostDTO);

        if (!currentPost.getTitle().equalsIgnoreCase(inPost.getTitle())) {
            // title changed
            currentPost.setTitle(updatePostDTO.getTitle());
            currentPost.setSlug(stringGeneratorService.generateSlug(updatePostDTO.getTitle()));
        }
        currentPost.setText(inPost.getText());
        currentPost.setParentLibrary(inPost.getParentLibrary());
        currentPost.setLibraryIndex(inPost.getLibraryIndex());


        Post savedPost = postRepository.save(currentPost);

        updatePostLibraryIndexOrder(savedPost);

        return new PostModelResponse(savedPost);
    }
}
