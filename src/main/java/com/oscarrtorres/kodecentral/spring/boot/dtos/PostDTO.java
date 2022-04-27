package com.oscarrtorres.kodecentral.spring.boot.dtos;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PostDTO {

    @NotNull(message = "title is required")
    @Length(min = 1, message = "title can not be blank")
    private String title;

    @NotNull(message = "library is required")
    @Length(min = 1, message = "library can not be blank")
    private String library;

    private String post;

    @NotNull(message = "libraryIndex is required")
    private int libraryIndex;

    @NotNull(message = "text is required")
    @Length(min = 1, message = "text can not be blank")
    private String text;

    public Post getPostModel() {
        Post p = new Post();
        p.setTitle(this.getTitle());
        p.setLibraryIndex(this.libraryIndex);
        p.setText(this.getText());

        return p;
    }
}