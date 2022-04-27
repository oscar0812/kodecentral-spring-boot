package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.dtos.PostDTO;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public PostModelResponse update(@RequestBody @Valid PostDTO updatePostDTO) {
        return postService.update(updatePostDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostModelResponse save(@RequestBody @Valid PostDTO newPostDTO) {
        return postService.save(newPostDTO);
    }
}
