package com.oscarrtorres.kodecentral.spring.boot.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "confirmation_key", nullable = false, length = 32)
    private String confirmationKey;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant UpdatedAt;
}