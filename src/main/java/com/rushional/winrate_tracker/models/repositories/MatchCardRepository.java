package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.CardStatsDTO;
import com.rushional.winrate_tracker.models.GeneralDeckStatsDTO;
import com.rushional.winrate_tracker.models.entities.MatchCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchCardRepository extends JpaRepository<MatchCard, Long> {

    @Query("SELECT NEW com.rushional.winrate_tracker.models.GeneralDeckStatsDTO(" +
        "AVG(m.cubeGain), " +
        "(SUM(CASE WHEN (m.cubeGain > 0) THEN 1 ELSE 0 END) * 1d) / (COUNT(m) * 1d), " + // winrate
        "SUM(m.cubeGain), " +
        "COUNT(m), " +
        "(SUM(CASE WHEN ((m.userSnapped = FALSE) AND (m.cubeGain >= 0)) THEN 1 ELSE 0 END) * 1d) / COUNT(m), " + //missed snaps
        "(SUM(CASE WHEN ((m.userSnapped = TRUE) AND (m.cubeGain < 0)) THEN 1 ELSE 0 END) * 1d) / COUNT(m)" + //snaps when lost
        ") FROM Match m WHERE m.deck.id = :deckId")
    GeneralDeckStatsDTO getDeckStats(@Param("deckId") Long deckId);

    @Query("SELECT NEW com.rushional.winrate_tracker.models.CardStatsDTO(" +
        "c.id, " +
        "c.name, " +
        "(SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) * 1d)" +
        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END), " + //playrate
        "(SUM(CASE WHEN (mc.isDrawn = TRUE) THEN m.cubeGain ELSE 0 END) * 1d)" +
        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END), " + //drawn cube gain
        "(SUM(CASE WHEN (mc.isPlayed = TRUE) THEN m.cubeGain ELSE 0 END) * 1d)" +
        " / SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END), " + //played cube gain
        "(SUM(CASE WHEN (m.cubeGain > 0) AND (mc.isDrawn = TRUE) THEN 1 ELSE 0 END) * 1d)" +
        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END), " + //drawn winrate
        "(SUM(CASE WHEN (m.cubeGain > 0) AND (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) * 1d)" +
        " / SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) " + //played winrate
        ") FROM MatchCard mc " +
        "INNER JOIN mc.match m " +
        "INNER JOIN mc.card c " +
        "WHERE m.deck.id = :deckId " +
        "GROUP BY c.id, c.name")
    List<CardStatsDTO> getCardsStats(@Param("deckId") Long deckId);
}
