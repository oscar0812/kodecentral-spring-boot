package com.oscarrtorres.kodecentral.spring.boot.repositories;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {
    List<Library> findAll();

    Library findBySlug(String librarySlug);
}
