package com.fantasyaew.aew_fantasy_league.wrestler.service;

import com.fantasyaew.aew_fantasy_league.wrestler.dto.FinisherDto;
import com.fantasyaew.aew_fantasy_league.wrestler.dto.WrestlerRequest;
import com.fantasyaew.aew_fantasy_league.wrestler.dto.WrestlerResponse;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Finisher;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import com.fantasyaew.aew_fantasy_league.wrestler.event.WrestlerEventProducer;
import com.fantasyaew.aew_fantasy_league.wrestler.repository.FinisherRepository;
import com.fantasyaew.aew_fantasy_league.wrestler.repository.WrestlerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WrestlerService {

    private final WrestlerRepository wrestlerRepository;
    private final FinisherRepository finisherRepository;
    private final WrestlerEventProducer eventProducer;

    @Transactional
    public WrestlerResponse create(WrestlerRequest request) {
        Wrestler wrestler = mapToEntity(new Wrestler(), request);
        Wrestler saved = wrestlerRepository.save(wrestler);
        eventProducer.sendCreated(saved);
        return mapToResponse(saved);
    }

    @Transactional
    public WrestlerResponse update(UUID id, WrestlerRequest request) {
        Wrestler wrestler = wrestlerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wrestler not found: " + id));
        mapToEntity(wrestler, request);
        Wrestler saved = wrestlerRepository.save(wrestler);
        eventProducer.sendUpdated(saved);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public WrestlerResponse getById(UUID id) {
        Wrestler wrestler = wrestlerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wrestler not found: " + id));
        return mapToResponse(wrestler);
    }

    @Transactional(readOnly = true)
    public List<WrestlerResponse> getAll() {
        return wrestlerRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public void delete(UUID id) {
        if (!wrestlerRepository.existsById(id)) {
            throw new EntityNotFoundException("Wrestler not found: " + id);
        }
        wrestlerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<WrestlerResponse> getPopular(Integer limit, Wrestler.Promotion promotion) {
        int size = (limit == null || limit <= 0 || limit > 100) ? 10 : limit;
        Pageable pageable = PageRequest.of(0, size);

        Page<Wrestler> page = (promotion != null)
                ? wrestlerRepository.findByPromotionOrderByPopularityScoreDesc(promotion, pageable)
                : wrestlerRepository.findAllByOrderByPopularityScoreDesc(pageable);

        return page.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public WrestlerResponse updatePopularity(UUID id, int popularityScore) {
        if (popularityScore < 0 || popularityScore > 100) {
            throw new IllegalArgumentException("popularityScore must be between 0 and 100");
        }
        Wrestler wrestler = wrestlerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wrestler not found: " + id));
        wrestler.setPopularityScore(popularityScore);
        Wrestler saved = wrestlerRepository.save(wrestler);
        eventProducer.sendUpdated(saved);
        return mapToResponse(saved);
    }

    private Wrestler mapToEntity(Wrestler wrestler, WrestlerRequest request) {
        wrestler.setRingName(request.getRingName());
        wrestler.setRealName(request.getRealName());
        wrestler.setNickname(request.getNickname());
        wrestler.setGender(request.getGender());
        wrestler.setDateOfBirth(request.getDateOfBirth());
        wrestler.setCountry(request.getCountry());
        wrestler.setHeightCm(request.getHeightCm());
        wrestler.setWeightKg(request.getWeightKg());
        wrestler.setPromotion(request.getPromotion());
        wrestler.setDivision(request.getDivision());
        wrestler.setWrestlingStyle(request.getWrestlingStyle());
        wrestler.setAlignment(request.getAlignment());
        wrestler.setImageUrl(request.getImageUrl());
        wrestler.setInjuryStatus(request.getInjuryStatus());
        wrestler.setSuspension(request.getSuspension());
        wrestler.setCareerWins(request.getCareerWins());
        wrestler.setCareerLosses(request.getCareerLosses());
        wrestler.setPopularityScore(request.getPopularityScore());
        return wrestler;
    }

    private WrestlerResponse mapToResponse(Wrestler wrestler) {
        List<Finisher> finishers = finisherRepository.findByWrestler(wrestler);
        List<FinisherDto> finisherDtos = finishers.stream()
                .map(f -> FinisherDto.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .description(f.getDescription())
                        .build())
                .toList();

        return WrestlerResponse.builder()
                .id(wrestler.getId())
                .ringName(wrestler.getRingName())
                .realName(wrestler.getRealName())
                .nickname(wrestler.getNickname())
                .gender(wrestler.getGender())
                .dateOfBirth(wrestler.getDateOfBirth())
                .country(wrestler.getCountry())
                .heightCm(wrestler.getHeightCm())
                .weightKg(wrestler.getWeightKg())
                .promotion(wrestler.getPromotion())
                .division(wrestler.getDivision())
                .wrestlingStyle(wrestler.getWrestlingStyle())
                .alignment(wrestler.getAlignment())
                .imageUrl(wrestler.getImageUrl())
                .injuryStatus(wrestler.getInjuryStatus())
                .suspension(wrestler.getSuspension())
                .careerWins(wrestler.getCareerWins())
                .careerLosses(wrestler.getCareerLosses())
                .popularityScore(wrestler.getPopularityScore())
                .createdAt(wrestler.getCreatedAt())
                .updatedAt(wrestler.getUpdatedAt())
                .finishers(finisherDtos)
                .build();
    }
}

