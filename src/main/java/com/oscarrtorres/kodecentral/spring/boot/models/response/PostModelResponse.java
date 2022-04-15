package com.oscarrtorres.kodecentral.spring.boot.models.response;


import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import lombok.*;

import java.time.Instant;

@ToString
@Getter
@Setter
public class PostModelResponse {
    private Long id;
    private String slug;
    private String title;
    private String text;
    private Post previousPost;
    private Post nextPost;

    private String parentLibrarySlug;
    private String parentLibraryName;

    private Instant createdAt;
    private Instant updatedAt;

    public PostModelResponse(Post post) {
        this.id = post.getId();
        this.slug = post.getSlug();
        this.title = post.getTitle();
        this.text = post.getText();
        this.previousPost = post.getPreviousPost();
        this.nextPost = post.getNextPost();
        this.parentLibrarySlug = post.getParentLibrary().getSlug();
        this.parentLibraryName = post.getParentLibrary().getName();

        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}