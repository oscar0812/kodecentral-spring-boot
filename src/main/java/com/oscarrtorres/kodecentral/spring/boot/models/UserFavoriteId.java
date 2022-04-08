package com.oscarrtorres.kodecentral.spring.boot.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class UserFavoriteId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1592365846785536321L;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Override
    public int hashCode() {
        return Objects.hash(postId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserFavoriteId entity = (UserFavoriteId) o;
        return Objects.equals(this.postId, entity.postId) &&
                Objects.equals(this.userId, entity.userId);
    }
}