package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LibraryResponse {
    private long libraryId;
    private String slug;
    private String name;

    private UserResponse createdByUser;

    private List<PostResponse> childPosts;

    public LibraryResponse(Library library) {
        this.libraryId = library.getId();
        this.slug = library.getSlug();
        this.name = library.getName();
        this.createdByUser = new UserResponse(library.getCreatedByUser());
        this.childPosts = library.getChildPosts().stream().map(PostResponse::new).toList();
    }
}
