package com.fantasyaew.aew_fantasy_league.championship.repository;

import com.fantasyaew.aew_fantasy_league.championship.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChampionshipRepository extends JpaRepository<Championship, UUID> {
}
