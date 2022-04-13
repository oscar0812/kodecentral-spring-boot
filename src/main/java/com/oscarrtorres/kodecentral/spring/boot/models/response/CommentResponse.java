package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CommentResponse {
    private long commentId;
    private String text;
    private UserResponse createdByUser;
    private PostResponse parentPost;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.text = comment.getText();
        this.createdByUser = new UserResponse(comment.getCreatedByUser());
        this.parentPost = new PostResponse(comment.getParentPost());
    }
}
