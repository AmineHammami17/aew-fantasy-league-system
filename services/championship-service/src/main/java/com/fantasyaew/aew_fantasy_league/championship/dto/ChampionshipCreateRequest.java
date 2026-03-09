package com.fantasyaew.aew_fantasy_league.championship.dto;

import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChampionshipCreateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Championship.Promotion promotion;

    @NotNull
    private Championship.Division division;

    @NotBlank
    @Size(max = 500)
    private String beltImageUrl;

    @Size(max = 500)
    private String logoImageUrl;

    @NotNull
    private Boolean active;

    @Min(1)
    @Max(100)
    private Integer prestigeLevel;
}
