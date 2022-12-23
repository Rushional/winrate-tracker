package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
