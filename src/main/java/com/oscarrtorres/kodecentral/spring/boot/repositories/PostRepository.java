package com.oscarrtorres.kodecentral.spring.boot.repositories;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findBySlug(String slug);
}
