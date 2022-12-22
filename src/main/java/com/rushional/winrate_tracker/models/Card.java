package com.rushional.winrate_tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
// Without ignoring the deckList entry JSON gives infinite recursion between a card and it's deck
@JsonIgnoreProperties({"matchesAssociation", "decksList"})
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
