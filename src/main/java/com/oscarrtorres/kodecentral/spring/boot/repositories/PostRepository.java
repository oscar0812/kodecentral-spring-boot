package com.oscarrtorres.kodecentral.spring.boot.repositories;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();

    @Query(value = "SELECT * " +
            "FROM post p " +
            "ORDER BY p.created_at DESC " +
            "LIMIT :limit", nativeQuery = true)
    List<Post> findLatest(@Param("limit") int limit);

    List<Post> findBySlug(String slug);

    List<Post> findByParentLibrarySlug(String librarySlug);
}
