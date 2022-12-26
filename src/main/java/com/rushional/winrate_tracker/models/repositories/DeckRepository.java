package com.rushional.winrate_tracker.models.repositories;

import com.rushional.winrate_tracker.models.CardStatsDTO;
import com.rushional.winrate_tracker.models.DeckStatsDTO;
import com.rushional.winrate_tracker.models.entities.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

//    @Query("SELECT (SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) * 1d)" +
//        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END), " +
//        "mc.card.id " +
//        "FROM MatchCard mc " +
//        "INNER JOIN mc.match m " +
//        "WHERE m.deck.id = :deckId " +
//        "GROUP BY mc.card")
//    List<Double> getPlayrates(@Param("deckId") Long deckId);
//
//    @Query("SELECT (SUM(CASE WHEN (mc.isDrawn = TRUE) THEN m.cubeGain ELSE 0 END) * 1d)" +
//        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END) " +
//        "FROM MatchCard mc " +
//        "INNER JOIN mc.match m " +
//        "WHERE m.deck.id = :deckId " +
//        "GROUP BY mc.card")
//    List<Double> getDrawnCubeGains(@Param("deckId") Long deckId);
//
//    @Query("SELECT (SUM(CASE WHEN (mc.isPlayed = TRUE) THEN m.cubeGain ELSE 0 END) * 1d)" +
//        " / SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) " +
//        "FROM MatchCard mc " +
//        "INNER JOIN mc.match m " +
//        "WHERE m.deck.id = :deckId " +
//        "GROUP BY mc.card")
//    List<Double> getPlayedCubeGains(@Param("deckId") Long deckId);
//
//    @Query("SELECT (SUM(CASE WHEN (m.cubeGain > 0) AND (mc.isDrawn = TRUE) THEN 1 ELSE 0 END) * 1d)" +
//        " / SUM(CASE WHEN (mc.isDrawn = TRUE) THEN 1 ELSE 0 END) " +
//        "FROM MatchCard mc " +
//        "INNER JOIN mc.match m " +
//        "WHERE m.deck.id = :deckId " +
//        "GROUP BY mc.card")
//    List<Double> getDrawnWinrates(@Param("deckId") Long deckId);
//
//    @Query("SELECT (SUM(CASE WHEN (m.cubeGain > 0) AND (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) * 1d)" +
//        " / SUM(CASE WHEN (mc.isPlayed = TRUE) THEN 1 ELSE 0 END) " +
//        "FROM MatchCard mc " +
//        "INNER JOIN mc.match m " +
//        "WHERE m.deck.id = :deckId " +
//        "GROUP BY mc.card")
//    List<Double> getPlayedWinrates(@Param("deckId") Long deckId);

//    @Query("SELECT AVG(m.cubeGain) FROM Match m WHERE m.deck.id = :deckId")
//    Double getAverageCubeGain(@Param("deckId") Long deckId);
//
//    @Query("SELECT SUM(CASE WHEN (m.cubeGain > 0) THEN 1 ELSE 0 END)/COUNT(m) FROM Match m WHERE m.deck.id = :deckId")
//    Integer getWinrate(@Param("deckId") Long deckId);
//
//    @Query("SELECT SUM(m.cubeGain) FROM Match m WHERE m.deck.id = :deckId")
//    Integer getCubesTotal(@Param("deckId") Long deckId);
//
//    @Query("SELECT COUNT(m) FROM Match m WHERE m.deck.id = :deckId")
//    Integer getMatchesAmount(@Param("deckId") Long deckId);
//
//    @Query("SELECT SUM(CASE WHEN ((m.userSnapped = FALSE) AND (m.cubeGain >= 0)) THEN 1 ELSE 0 END) " +
//        "FROM Match m WHERE m.deck.id = :deckId")
//    Integer getMissedSnapsAmount(@Param("deckId") Long deckId);
//
//    @Query("SELECT SUM(CASE WHEN ((m.userSnapped = TRUE) AND (m.cubeGain < 0)) THEN 1 ELSE 0 END) " +
//        "FROM Match m WHERE m.deck.id = :deckId")
//    Integer getLoosingSnapsAmount(@Param("deckId") Long deckId);
}
