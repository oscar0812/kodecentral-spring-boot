package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.response.LibraryResponse;
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

    public List<LibraryResponse> findAll() {
        return this.libraryRepository.findAll().stream().map(LibraryResponse::new).toList();
    }

    public LibraryResponse save(Library library) {
        library.setSlug(stringGeneratorService.generateSlug(library.getName()));
        Library savedLibrary = this.libraryRepository.save(library);
        return new LibraryResponse(savedLibrary);
    }
}
