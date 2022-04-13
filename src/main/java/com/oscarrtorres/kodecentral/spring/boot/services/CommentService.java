package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CommentResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentResponse> findAll() {
        return commentRepository.findAll().stream().map(CommentResponse::new).toList();
    }

    public CommentResponse save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(savedComment);
    }
}
