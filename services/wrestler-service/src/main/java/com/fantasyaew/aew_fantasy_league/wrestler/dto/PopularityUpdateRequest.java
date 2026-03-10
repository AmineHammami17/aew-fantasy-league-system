package com.fantasyaew.aew_fantasy_league.wrestler.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Value;

@Value
public class PopularityUpdateRequest {

    @Min(0)
    @Max(100)
    int popularityScore;
}

