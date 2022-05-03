package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CommentModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.models.response.PostModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.PostRepository;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import com.oscarrtorres.kodecentral.spring.boot.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "${angular.cors.url}")
@RestController
@RequestMapping("${spring.data.rest.basePath}/comment")
public class CommentRestController {
    private final CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentModelResponse> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/user")
    public List<CommentModelResponse> findByUser(@RequestParam("username") String username) {
        return commentService.findByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentModelResponse save(@RequestBody @Valid Comment comment) {
        return commentService.save(comment);
    }

//    @PostMapping("/list")
//    @Transactional
//    public void saveAll(@RequestBody List<Comment> commentList) {
//        for(Comment comment: commentList) {
//            comment.setCreatedByUser(userRepository.findById(comment.getUserId()).get());
//            comment.setParentPost(postRepository.findById(comment.getPostId()).get());
//            comment.setCreatedAt(comment.getPostedTime());
//        }
//
//        for(Comment comment: commentList) {
//            commentService.save(comment);
//        }
//    }
}
