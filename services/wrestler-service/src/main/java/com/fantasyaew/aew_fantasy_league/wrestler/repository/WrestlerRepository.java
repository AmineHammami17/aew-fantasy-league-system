package com.fantasyaew.aew_fantasy_league.wrestler.repository;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WrestlerRepository extends JpaRepository<Wrestler, UUID> {

    Page<Wrestler> findAllByOrderByPopularityScoreDesc(Pageable pageable);

    Page<Wrestler> findByPromotionOrderByPopularityScoreDesc(Wrestler.Promotion promotion, Pageable pageable);
}

