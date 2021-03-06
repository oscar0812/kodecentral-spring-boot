package com.oscarrtorres.kodecentral.spring.boot.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "title", nullable = false, length = 128)
    @NotNull(message = "title is required")
    @Length(min = 1, message = "title can not be blank")
    private String title;

    @Lob
    @Column(name = "text", nullable = false)
    @NotNull(message = "text is required")
    @Length(min = 1, message = "text can not be blank")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "posted_by_user_id", nullable = false)
    @ToString.Exclude
    @CreatedBy
    private User createdByUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "library_id", nullable = false)
    @ToString.Exclude
    private Library parentLibrary;

    @ManyToMany
    @JoinTable(name = "user_favorite",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "parentPost")
    @ToString.Exclude
    private Set<Comment> comments = new LinkedHashSet<>();

    @Column(name = "library_index", nullable = false)
    private long libraryIndex = 0;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

//    @Transient
//    private int libraryId;
//
//    @Transient
//    private int postedByUserId;
//
//    @Transient
//    private String hyperlink;
//
//    @Transient
//    private Instant postedDate;
//
//    @Transient
//    private int creatorId;
}