package com.fantasyaew.aew_fantasy_league.championship.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TitleReignCreateRequest {

    @NotNull
    private UUID wrestlerId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private Boolean isCurrent;

    @NotNull
    private Integer defenseCount;
}
