package com.fantasyaew.aew_fantasy_league.championship.controller;

import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipCreateRequest;
import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipDto;
import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipUpdateRequest;
import com.fantasyaew.aew_fantasy_league.championship.service.ChampionshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/championships")
@RequiredArgsConstructor
public class ChampionshipController {

    private final ChampionshipService championshipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChampionshipDto create(@Valid @RequestBody ChampionshipCreateRequest request) {
        return championshipService.create(request);
    }

    @GetMapping
    public List<ChampionshipDto> getAll() {
        return championshipService.getAll();
    }

    @GetMapping("/{id}")
    public ChampionshipDto getById(@PathVariable UUID id) {
        return championshipService.getById(id);
    }

    @PutMapping("/{id}")
    public ChampionshipDto update(@PathVariable UUID id,
                                  @Valid @RequestBody ChampionshipUpdateRequest request) {
        return championshipService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        championshipService.delete(id);
    }
}
