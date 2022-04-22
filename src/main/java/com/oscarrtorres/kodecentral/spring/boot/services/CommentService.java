package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CommentModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentModelResponse> findAll() {
        return commentRepository.findAll().stream().map(CommentModelResponse::new).toList();
    }

    public List<CommentModelResponse> findByUsername(String username) {
        return commentRepository.findByCreatedByUserUsernameOrderByCreatedAt(username).stream().map(CommentModelResponse::new).toList();
    }

    public CommentModelResponse save(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return new CommentModelResponse(savedComment);
    }
}
