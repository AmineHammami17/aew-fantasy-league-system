package com.fantasyaew.aew_fantasy_league.wrestler.repository;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Finisher;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FinisherRepository extends JpaRepository<Finisher, UUID> {

    List<Finisher> findByWrestler(Wrestler wrestler);
}

