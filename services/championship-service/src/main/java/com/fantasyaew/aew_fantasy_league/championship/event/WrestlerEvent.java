package com.fantasyaew.aew_fantasy_league.championship.event;

import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class WrestlerEvent {

    private UUID id;
    private String ringName;
    private Championship.Promotion promotion;
    private Championship.Division division;
    private int popularityScore;
    private String eventType;
    private Instant occurredAt;
}

