package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
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

    public List<Library> findAll() {
        return this.libraryRepository.findAll();
    }

    public Library save(Library library) {
        library.setSlug(stringGeneratorService.generateSlug(library.getName()));
        Library savedLibrary = this.libraryRepository.save(library);
        return savedLibrary;
    }
}
