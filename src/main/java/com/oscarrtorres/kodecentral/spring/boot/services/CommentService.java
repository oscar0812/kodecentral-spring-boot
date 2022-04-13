package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
