package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.response.LibraryModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${spring.data.rest.basePath}/library")
public class LibraryRestController {
    private final LibraryService libraryService;

    public LibraryRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public List<LibraryModelResponse> findAll() {
        return libraryService.findAll();
    }

    @GetMapping("/slug")
    public LibraryModelResponse findBySlug(@RequestParam("slug") String librarySlug) {
        return libraryService.findBySlug(librarySlug);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryModelResponse save(@RequestBody @Valid Library library) {
        return libraryService.save(library);
    }
}
