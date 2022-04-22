package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@Setter
public class CommentModelResponse {
    private Long id;
    private String text;
    private UserModelResponse createdByUser;
    private String parentPostSlug;
    private String parentPostTitle;
    private Instant createdAt;
    private Instant updatedAt;

    public CommentModelResponse(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.createdByUser = new UserModelResponse(comment.getCreatedByUser());
        this.parentPostSlug = comment.getParentPost().getSlug();
        this.parentPostTitle = comment.getParentPost().getTitle();

        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}