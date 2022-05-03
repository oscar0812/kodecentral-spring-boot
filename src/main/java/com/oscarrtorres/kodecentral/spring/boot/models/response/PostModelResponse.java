package com.oscarrtorres.kodecentral.spring.boot.models.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
public class PostModelResponse {
    @JsonIgnore
    private Post post;
    private Long id;
    private String slug;
    private String title;
    private String text;
    private long libraryIndex;

    private PostModelResponse previousPost;
    private PostModelResponse nextPost;

    private LibraryModelResponse parentLibrary;
    private UserModelResponse createdByUser;
    private List<CommentModelResponse> comments;

    private Instant createdAt;
    private Instant updatedAt;

    public void calculatePreviousAndNextPost() {
        long index = post.getLibraryIndex();
        Set<Post> allPosts = post.getParentLibrary().getChildPosts();
        for(Post p: allPosts) {
            if(p.getLibraryIndex() == index - 1) {
                this.previousPost = new PostModelResponse(p);
            }
            else if(p.getLibraryIndex() == index + 1) {
                this.nextPost = new PostModelResponse(p);
            }
        }
    }

    public PostModelResponse(Post post) {
        this.post = post;
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