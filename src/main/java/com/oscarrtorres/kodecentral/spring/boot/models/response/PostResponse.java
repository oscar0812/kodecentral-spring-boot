package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostResponse {
    private Long postId;
    private String slug;
    private String title;
    private String text;
    private UserResponse createdByUser;

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.slug = post.getSlug();
        this.title = post.getTitle();
        this.text = post.getText();
        this.createdByUser = new UserResponse(post.getPostedByUser());
    }
}
