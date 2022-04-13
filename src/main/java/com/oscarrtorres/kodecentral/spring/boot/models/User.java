package com.oscarrtorres.kodecentral.spring.boot.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 64, unique = true)
    @NotNull(message = "username is required")
    @Length(min = 1, message = "username can not be blank")
    private String username;

    @Column(name = "email", nullable = false, length = 128, unique = true)
    @NotNull(message = "email is required")
    @Length(min = 1, message = "email can not be blank")
    @Email(message = "Email must be a well-formed email address")
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    @NotNull(message = "password is required")
    @Length(min = 1, message = "password can not be blank")
    private String password;

    @Column(name = "profile_picture", nullable = false, length = 128)
    private String profilePicture = "";

    @Column(name = "bio", nullable = false, length = 128)
    private String bio = "";

    @Column(name = "is_super", nullable = false)
    private Boolean isSuper = false;

    @Column(name = "confirmation_key", nullable = false, length = 32)
    private String confirmationKey = "";

    @Column(name = "reset_key", nullable = false, length = 32)
    private String resetKey = "";

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    @JsonIgnore
    @ToString.Exclude
    private Set<Post> favoritePosts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "postedByUser", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdByUser", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Library> libraries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "createdByUser", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private Set<Comment> comments = new LinkedHashSet<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant UpdatedAt;
}