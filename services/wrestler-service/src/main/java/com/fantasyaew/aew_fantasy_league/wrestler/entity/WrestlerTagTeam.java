package com.fantasyaew.aew_fantasy_league.wrestler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "wrestler_tag_teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WrestlerTagTeam {

    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "wrestler_id", nullable = false)
    private Wrestler wrestler;

    @Column(name = "tag_team_id", nullable = false)
    private UUID tagTeamId;

    @Column(name="tag_team_name",nullable = false)
    private String name;

    @PrePersist
    protected void onCreate() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}

