package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.DeckStatsDTO;
import com.rushional.winrate_tracker.models.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeckRepository extends JpaRepository<Deck, Long> {

    @Query("SELECT NEW com.rushional.winrate_tracker.models.DeckStatsDTO(" +
            "AVG(m.cubeGain), " +
            "(SUM(CASE WHEN (m.cubeGain > 0) THEN 1 ELSE 0 END) * 1d) / (COUNT(m) * 1d), " + // winrate
            "SUM(m.cubeGain), " +
            "COUNT(m), " +
            "(SUM(CASE WHEN ((m.userSnapped = FALSE) AND (m.cubeGain >= 0)) THEN 1 ELSE 0 END) * 1d) / COUNT(m), " + //missed snaps
            "(SUM(CASE WHEN ((m.userSnapped = TRUE) AND (m.cubeGain < 0)) THEN 1 ELSE 0 END) * 1d) / COUNT(m)" + //snaps when lost
        ") FROM Match m WHERE m.deck.id = :deckId")
    DeckStatsDTO getDeckStats(@Param("deckId") Long deckId);

    @Query("SELECT AVG(m.cubeGain) FROM Match m WHERE m.deck.id = :deckId")
    Double getAverageCubeGain(@Param("deckId") Long deckId);

    @Query("SELECT SUM(CASE WHEN (m.cubeGain > 0) THEN 1 ELSE 0 END)/COUNT(m) FROM Match m WHERE m.deck.id = :deckId")
    Integer getWinrate(@Param("deckId") Long deckId);

    @Query("SELECT SUM(m.cubeGain) FROM Match m WHERE m.deck.id = :deckId")
    Integer getCubesTotal(@Param("deckId") Long deckId);

    @Query("SELECT COUNT(m) FROM Match m WHERE m.deck.id = :deckId")
    Integer getMatchesAmount(@Param("deckId") Long deckId);

    @Query("SELECT SUM(CASE WHEN ((m.userSnapped = FALSE) AND (m.cubeGain >= 0)) THEN 1 ELSE 0 END) " +
        "FROM Match m WHERE m.deck.id = :deckId")
    Integer getMissedSnapsAmount(@Param("deckId") Long deckId);

    @Query("SELECT SUM(CASE WHEN ((m.userSnapped = TRUE) AND (m.cubeGain < 0)) THEN 1 ELSE 0 END) " +
        "FROM Match m WHERE m.deck.id = :deckId")
    Integer getLoosingSnapsAmount(@Param("deckId") Long deckId);
}
