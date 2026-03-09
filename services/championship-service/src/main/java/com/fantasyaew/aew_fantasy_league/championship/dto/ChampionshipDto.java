package com.fantasyaew.aew_fantasy_league.championship.dto;

import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
@Builder
public class ChampionshipDto {

    UUID id;
    String name;
    Championship.Promotion promotion;
    Championship.Division division;
    String beltImageUrl;
    String logoImageUrl;
    boolean active;
    int prestigeLevel;
    Instant createdAt;
    Instant updatedAt;
}
