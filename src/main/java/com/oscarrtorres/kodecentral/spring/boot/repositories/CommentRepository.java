package com.oscarrtorres.kodecentral.spring.boot.repositories;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAll();
}
