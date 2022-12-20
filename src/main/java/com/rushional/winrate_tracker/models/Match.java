package com.rushional.winrate_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany()
    @JoinTable(
        name = "match_card",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<Card> cardsList;

    private int cubeGain;
    private int turnsPlayed;
    boolean userSnapped;
    boolean opponentSnapped;
    boolean isOpponentBot;
    String notes;
}
