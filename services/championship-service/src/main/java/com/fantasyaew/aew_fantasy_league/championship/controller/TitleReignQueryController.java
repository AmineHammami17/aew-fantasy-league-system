package com.fantasyaew.aew_fantasy_league.championship.controller;

import com.fantasyaew.aew_fantasy_league.championship.dto.TitleReignDto;
import com.fantasyaew.aew_fantasy_league.championship.service.TitleReignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/title-reigns")
@RequiredArgsConstructor
public class TitleReignQueryController {

    private final TitleReignService titleReignService;

    @GetMapping("/by-wrestler/{wrestlerId}")
    public List<TitleReignDto> getByWrestler(@PathVariable UUID wrestlerId) {
        return titleReignService.getByWrestler(wrestlerId);
    }
}

