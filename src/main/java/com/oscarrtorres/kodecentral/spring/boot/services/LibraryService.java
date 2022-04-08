package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.repositories.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> findAll() {
        return this.libraryRepository.findAll();
    }

    public Library save(Library library) {
        return this.libraryRepository.save(library);
    }
}
