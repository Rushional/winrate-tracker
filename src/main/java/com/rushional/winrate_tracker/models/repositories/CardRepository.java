package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
