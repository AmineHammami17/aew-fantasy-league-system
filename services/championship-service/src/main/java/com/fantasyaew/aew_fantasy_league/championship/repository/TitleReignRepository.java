package com.fantasyaew.aew_fantasy_league.championship.repository;

import com.fantasyaew.aew_fantasy_league.championship.entity.TitleReign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TitleReignRepository extends JpaRepository<TitleReign, UUID> {

    List<TitleReign> findByChampionship_IdOrderByStartDateDesc(UUID championshipId);
}
