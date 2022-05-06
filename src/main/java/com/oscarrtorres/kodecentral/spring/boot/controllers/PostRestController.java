package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.dtos.PostDTO;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.LibraryRepository;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import com.oscarrtorres.kodecentral.spring.boot.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "${angular.cors.url}")
@RestController
@RequestMapping("${spring.data.rest.basePath}/post")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostModelResponse> findAll() {
        return postService.findAll();
    }

    @GetMapping("/latest")
    public List<PostModelResponse> findLatest(@RequestParam("limit") int limit) {
        return postService.findLatest(limit);
    }

    @GetMapping("/library")
    public List<PostModelResponse> findByParentLibrarySlug(@RequestParam("slug") String librarySlug) {
        return postService.findByParentLibrarySlug(librarySlug);
    }

    @GetMapping("/slug")
    public PostModelResponse findBySlug(@RequestParam("slug") String librarySlug) {
        return postService.findBySlug(librarySlug);
    }

    @GetMapping("/user")
    public List<PostModelResponse> findByUser(@RequestParam("username") String username) {
        return postService.findByUsername(username);
    }

    @GetMapping("/search")
    public List<PostModelResponse> searchPostText(@RequestParam("text") String text) {
        return postService.getPostWhereTextContainsAnyWord(text);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public PostModelResponse update(@RequestBody @Valid PostDTO updatePostDTO) {
        return postService.update(updatePostDTO);
    }

//    @PostMapping("/list")
//    @ResponseStatus(HttpStatus.CREATED)
//    @Transactional
//    public void saveList(@RequestBody List<Post> postList) {
//
//        List<User> allUsers = userRepository.findAll();
//        for (Post post: postList) {
//            post.setSlug(post.getHyperlink());
//
//            post.setCreatedByUser(this.userRepository.findById(post.getCreatorId()).get());
//            post.setParentLibrary(this.libraryRepository.findById((long)post.getLibraryId()).get());
//            post.setCreatedAt(post.getPostedDate());
//        }
//
//        for(Post post: postList) {
//            postRepository.save(post);
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostModelResponse save(@RequestBody @Valid PostDTO newPostDTO) {
        return postService.save(newPostDTO);
    }
}
