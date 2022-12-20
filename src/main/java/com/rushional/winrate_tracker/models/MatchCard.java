package com.rushional.winrate_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "match_card")
@Getter
@Setter
public class MatchCard {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;
    boolean isDrawn;
    boolean isPlayed;
}
