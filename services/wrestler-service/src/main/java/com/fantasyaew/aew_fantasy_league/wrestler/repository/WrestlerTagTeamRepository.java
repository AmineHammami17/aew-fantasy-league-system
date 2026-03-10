package com.fantasyaew.aew_fantasy_league.wrestler.repository;

import com.fantasyaew.aew_fantasy_league.wrestler.entity.Wrestler;
import com.fantasyaew.aew_fantasy_league.wrestler.entity.WrestlerTagTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WrestlerTagTeamRepository extends JpaRepository<WrestlerTagTeam, UUID> {

    List<WrestlerTagTeam> findByWrestler(Wrestler wrestler);

    List<WrestlerTagTeam> findByTagTeamId(UUID tagTeamId);
}

