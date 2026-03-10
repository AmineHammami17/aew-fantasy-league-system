package com.fantasyaew.aew_fantasy_league.wrestler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "wrestlers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wrestler {

    @Id
    private UUID id;

    @Column(name = "ring_name", nullable = false)
    private String ringName;

    @Column(name = "real_name")
    private String realName;

    @Column
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column
    private String country;

    @Column(name = "height_cm")
    private Integer heightCm;

    @Column(name = "weight_kg")
    private Integer weightKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Promotion promotion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Division division;

    @Column(name = "wrestling_style")
    private String wrestlingStyle;

    @Enumerated(EnumType.STRING)
    @Column
    private Alignment alignment;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "injury_status")
    private String injuryStatus;

    @Column
    private String suspension;

    @Column(name = "career_wins")
    private Integer careerWins;

    @Column(name = "career_losses")
    private Integer careerLosses;

    @Column(name = "popularity_score", nullable = false)
    @Builder.Default
    private int popularityScore = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum Promotion {
        AEW,
        NJPW,
        CMLL,
        ROH,
        STARDOM,
        INDIES
    }

    public enum Division {
        SINGLES,
        TAG,
        TRIOS,
        OTHER
    }

    public enum Alignment {
        FACE,
        HEEL,
        TWEENER
    }
}

