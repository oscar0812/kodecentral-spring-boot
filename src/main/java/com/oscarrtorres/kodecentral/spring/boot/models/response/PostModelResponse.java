package com.oscarrtorres.kodecentral.spring.boot.models.response;


import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import lombok.*;

import java.time.Instant;
import java.util.List;

@ToString
@Getter
@Setter
public class PostModelResponse {
    private Long id;
    private String slug;
    private String title;
    private String text;
    private long libraryIndex;

    private LibraryModelResponse parentLibrary;
    private UserModelResponse createdByUser;
    private List<CommentModelResponse> comments;

    private Instant createdAt;
    private Instant updatedAt;

    public PostModelResponse(Post post) {
        this.id = post.getId();
        this.slug = post.getSlug();
        this.title = post.getTitle();
        this.text = post.getText();
        this.libraryIndex = post.getLibraryIndex();
        this.parentLibrary = new LibraryModelResponse(post.getParentLibrary());
        this.createdByUser = new UserModelResponse(post.getCreatedByUser());
        this.comments = post.getComments().stream().map(CommentModelResponse::new).toList();

        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}