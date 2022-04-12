package com.oscarrtorres.kodecentral.spring.boot.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "name", nullable = false, length = 128)
    @NotNull(message = "Name is required")
    @Length(min = 1, message = "Name can not be blank")
    private String name;

    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Post> posts = new LinkedHashSet<>();

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant UpdatedAt;
}