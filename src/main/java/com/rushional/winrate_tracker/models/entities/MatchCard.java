package com.rushional.winrate_tracker.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "match_card")
@Getter
@Setter
@JsonIgnoreProperties({"match"})
public class MatchCard {

    public MatchCard() {}

    public MatchCard(Match match, Card card, boolean isDrawn, boolean isPlayed) {
        this.match = match;
        this.card = card;
        this.isDrawn = isDrawn;
        this.isPlayed = isPlayed;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(
        cascade = CascadeType.PERSIST
    )
    @JoinColumn(name = "match_id", referencedColumnName = "id")
    private Match match;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;
    boolean isDrawn;
    boolean isPlayed;
}
