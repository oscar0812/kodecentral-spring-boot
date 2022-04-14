package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CommentModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${spring.data.rest.basePath}/comment")
public class CommentRestController {
    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentModelResponse> findAll() {
        return commentService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModelResponse save(@RequestBody @Valid Comment comment) {
        return commentService.save(comment);
    }
}
