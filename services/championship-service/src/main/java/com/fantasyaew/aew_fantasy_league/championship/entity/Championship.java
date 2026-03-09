package com.fantasyaew.aew_fantasy_league.championship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "championships")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Championship {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Promotion promotion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Division division;

    @Column(name = "belt_image_url", nullable = false, length = 500)
    private String beltImageUrl;

    @Column(name = "logo_image_url", length = 500)
    private String logoImageUrl;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(name = "prestige_level", nullable = false)
    private int prestigeLevel;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(
            mappedBy = "championship",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TitleReign> titleReigns;

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
}
