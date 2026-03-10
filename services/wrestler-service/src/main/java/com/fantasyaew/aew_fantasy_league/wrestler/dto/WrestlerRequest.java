package com.fantasyaew.aew_fantasy_league.wrestler.dto;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Alignment;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Division;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Gender;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler.Promotion;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class WrestlerRequest {

    @NotBlank
    String ringName;

    String realName;

    String nickname;

    @NotNull
    Gender gender;

    LocalDate dateOfBirth;

    String country;

    Integer heightCm;

    Integer weightKg;

    @NotNull
    Promotion promotion;

    @NotNull
    Division division;

    String wrestlingStyle;

    Alignment alignment;

    String imageUrl;

    String injuryStatus;

    String suspension;

    Integer careerWins;

    Integer careerLosses;

    @Min(0)
    @Max(100)
    int popularityScore;
}

