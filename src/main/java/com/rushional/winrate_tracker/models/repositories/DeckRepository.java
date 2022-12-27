package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
