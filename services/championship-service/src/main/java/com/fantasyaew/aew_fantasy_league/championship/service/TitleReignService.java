package com.fantasyaew.aew_fantasy_league.championship.service;

import com.fantasyaew.aew_fantasy_league.championship.dto.TitleReignCreateRequest;
import com.fantasyaew.aew_fantasy_league.championship.dto.TitleReignDto;
import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import com.fantasyaew.aew_fantasy_league.championship.entity.TitleReign;
import com.fantasyaew.aew_fantasy_league.championship.repository.ChampionshipRepository;
import com.fantasyaew.aew_fantasy_league.championship.repository.TitleReignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleReignService {

    private final TitleReignRepository titleReignRepository;
    private final ChampionshipRepository championshipRepository;

    public TitleReignDto create(UUID championshipId, TitleReignCreateRequest request) {
        Championship championship = championshipRepository.findById(championshipId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        TitleReign reign = TitleReign.builder()
                .championship(championship)
                .wrestlerId(request.getWrestlerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrent(request.getIsCurrent())
                .defenseCount(request.getDefenseCount())
                .build();

        TitleReign saved = titleReignRepository.save(reign);
        return toDto(saved);
    }

    public List<TitleReignDto> getByChampionship(UUID championshipId) {
        return titleReignRepository.findByChampionship_IdOrderByStartDateDesc(championshipId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TitleReignDto toDto(TitleReign reign) {
        return TitleReignDto.builder()
                .id(reign.getId())
                .championshipId(reign.getChampionship() != null ? reign.getChampionship().getId() : null)
                .wrestlerId(reign.getWrestlerId())
                .startDate(reign.getStartDate())
                .endDate(reign.getEndDate())
                .isCurrent(reign.isCurrent())
                .defenseCount(reign.getDefenseCount())
                .createdAt(reign.getCreatedAt())
                .build();
    }
}
