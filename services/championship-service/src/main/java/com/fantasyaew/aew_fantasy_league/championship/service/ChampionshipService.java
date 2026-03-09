package com.fantasyaew.aew_fantasy_league.championship.service;

import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipCreateRequest;
import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipDto;
import com.fantasyaew.aew_fantasy_league.championship.dto.ChampionshipUpdateRequest;
import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import com.fantasyaew.aew_fantasy_league.championship.repository.ChampionshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    public ChampionshipDto create(ChampionshipCreateRequest request) {
        Championship championship = Championship.builder()
                .name(request.getName())
                .promotion(request.getPromotion())
                .division(request.getDivision())
                .beltImageUrl(request.getBeltImageUrl())
                .logoImageUrl(request.getLogoImageUrl())
                .active(request.getActive())
                .prestigeLevel(request.getPrestigeLevel())
                .build();

        Championship saved = championshipRepository.save(championship);
        return toDto(saved);
    }

    public List<ChampionshipDto> getAll() {
        return championshipRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ChampionshipDto getById(UUID id) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));
        return toDto(championship);
    }

    public ChampionshipDto update(UUID id, ChampionshipUpdateRequest request) {
        Championship championship = championshipRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found"));

        championship.setName(request.getName());
        championship.setPromotion(request.getPromotion());
        championship.setDivision(request.getDivision());
        championship.setBeltImageUrl(request.getBeltImageUrl());
        championship.setLogoImageUrl(request.getLogoImageUrl());
        championship.setActive(request.getActive());
        championship.setPrestigeLevel(request.getPrestigeLevel());

        Championship saved = championshipRepository.save(championship);
        return toDto(saved);
    }

    public void delete(UUID id) {
        if (!championshipRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Championship not found");
        }
        championshipRepository.deleteById(id);
    }

    private ChampionshipDto toDto(Championship championship) {
        return ChampionshipDto.builder()
                .id(championship.getId())
                .name(championship.getName())
                .promotion(championship.getPromotion())
                .division(championship.getDivision())
                .beltImageUrl(championship.getBeltImageUrl())
                .logoImageUrl(championship.getLogoImageUrl())
                .active(championship.isActive())
                .prestigeLevel(championship.getPrestigeLevel())
                .createdAt(championship.getCreatedAt())
                .updatedAt(championship.getUpdatedAt())
                .build();
    }
}
