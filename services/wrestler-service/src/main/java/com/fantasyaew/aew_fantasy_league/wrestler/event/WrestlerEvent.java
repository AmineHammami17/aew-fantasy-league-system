package com.fantasyaew.aew_fantasy_league.wrestler.event;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
@Builder
public class WrestlerEvent {

    UUID id;
    String ringName;
    Wrestler.Promotion promotion;
    Wrestler.Division division;
    int popularityScore;
    String eventType;
    Instant occurredAt;
}

