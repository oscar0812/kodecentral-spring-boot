package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;

public class Specifications {
    public static Specification<Post> postTextContains(String searchWord) {
        return (root, query, builder) -> {
            Expression<String> textLowerCase = builder.lower(root.get("text"));
            return builder.like(textLowerCase, "%" + searchWord.toLowerCase() + "%");
        };
    }
}
