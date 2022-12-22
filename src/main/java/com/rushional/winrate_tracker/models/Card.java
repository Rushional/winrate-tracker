package com.rushional.winrate_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Card {
    public Card() {}

    public Card(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "cardsList")
    List<Deck> decksList;

    @OneToMany(mappedBy = "card")
    List<MatchCard> matchesAssociation;
}
