package com.fantasyaew.aew_fantasy_league.championship.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class TitleReignDto {

    UUID id;
    UUID championshipId;
    UUID wrestlerId;
    LocalDate startDate;
    LocalDate endDate;
    boolean isCurrent;
    int defenseCount;
    Instant createdAt;
}
