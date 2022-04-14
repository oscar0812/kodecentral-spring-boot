package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/library")
    public List<PostModelResponse> findByParentLibrarySlug(@RequestParam("slug") String librarySlug) {
        return postService.findByParentLibrarySlug(librarySlug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostModelResponse save(@RequestBody @Valid Post post) {
        return postService.save(post);
    }
}
