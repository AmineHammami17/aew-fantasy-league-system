package com.fantasyaew.aew_fantasy_league.wrestler.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class FinisherDto {

    UUID id;
    String name;
    String description;
}

