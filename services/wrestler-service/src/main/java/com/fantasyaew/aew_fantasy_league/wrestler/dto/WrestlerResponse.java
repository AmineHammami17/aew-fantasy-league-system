package com.fantasyaew.aew_fantasy_league.wrestler.dto;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Alignment;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Division;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Gender;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Promotion;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class WrestlerResponse {

    UUID id;
    String ringName;
    String realName;
    String nickname;
    Gender gender;
    LocalDate dateOfBirth;
    String country;
    Integer heightCm;
    Integer weightKg;
    Promotion promotion;
    Division division;
    String wrestlingStyle;
    Alignment alignment;
    String imageUrl;
    String injuryStatus;
    String suspension;
    Integer careerWins;
    Integer careerLosses;
    int popularityScore;
    Instant createdAt;
    Instant updatedAt;

    List<FinisherDto> finishers;
}

