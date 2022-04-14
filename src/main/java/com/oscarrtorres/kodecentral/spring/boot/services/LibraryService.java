package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.response.LibraryModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.LibraryRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final StringGeneratorService stringGeneratorService;

    public LibraryService(LibraryRepository libraryRepository, @Lazy StringGeneratorService stringGeneratorService) {
        this.libraryRepository = libraryRepository;
        this.stringGeneratorService = stringGeneratorService;
    }

    public List<LibraryModelResponse> findAll() {
        return this.libraryRepository.findAll().stream().map(LibraryModelResponse::new).toList();
    }

    public LibraryModelResponse findBySlug(String librarySlug) {
        Library library = this.libraryRepository.findBySlug(librarySlug);
        return new LibraryModelResponse(library);
    }

    public LibraryModelResponse save(Library library) {
        library.setSlug(stringGeneratorService.generateSlug(library.getName()));
        Library savedLibrary = this.libraryRepository.save(library);
        return new LibraryModelResponse(savedLibrary);
    }
}
