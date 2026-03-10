package com.fantasyaew.aew_fantasy_league.championship.controller;

import com.fantasyaew.aew_fantasy_league.championship.dto.TitleReignCreateRequest;
import com.fantasyaew.aew_fantasy_league.championship.dto.TitleReignDto;
import com.fantasyaew.aew_fantasy_league.championship.service.TitleReignService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/championships/{championshipId}/reigns")
@RequiredArgsConstructor
public class TitleReignController {

    private final TitleReignService titleReignService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TitleReignDto create(@PathVariable UUID championshipId,
                                @Valid @RequestBody TitleReignCreateRequest request) {
        return titleReignService.create(championshipId, request);
    }

    @GetMapping
    public List<TitleReignDto> getByChampionship(@PathVariable UUID championshipId) {
        return titleReignService.getByChampionship(championshipId);
    }

    @GetMapping("/current-reign")
    public TitleReignDto getCurrentByChampionship(@PathVariable UUID championshipId) {
        return titleReignService.getCurrentByChampionship(championshipId);
    }
}
