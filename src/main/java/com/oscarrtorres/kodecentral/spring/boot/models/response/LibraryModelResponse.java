package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import lombok.*;


import java.time.Instant;

@ToString
@Getter
@Setter
public class LibraryModelResponse {
    private Long id;
    private String slug;
    private String name;
    private Instant createdAt;

    private Instant updatedAt;

    public LibraryModelResponse(Library library) {
        this.id = library.getId();
        this.slug = library.getSlug();
        this.name = library.getName();
        this.createdAt = library.getCreatedAt();
        this.updatedAt = library.getUpdatedAt();
    }
}