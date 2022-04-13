package com.oscarrtorres.kodecentral.spring.boot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@ToString
@Getter
@Setter

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false, length = 512)
    @NotNull(message = "text is required")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    @CreatedBy
    private User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @NotNull(message = "parentPost is required")
    @ToString.Exclude
    @JsonIgnore
    private Post parentPost;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant UpdatedAt;
}