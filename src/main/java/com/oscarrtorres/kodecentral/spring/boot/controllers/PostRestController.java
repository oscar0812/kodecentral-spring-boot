package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.basePath}/post")
public class PostRestController {
    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post save(@RequestBody @Valid Post post) {
        return postService.save(post);
    }
}
