package com.rushional.winrate_tracker.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"matchesList"})
public class Deck {

    public Deck() {}

    public Deck(String deckCode, String notes) {
        this.deckCode = deckCode;
        this.notes = notes;
        cardsList = new ArrayList<>();
        matchesList = new ArrayList<>();
    }



    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "deck_code")
    String deckCode;
    String notes;

    @ManyToMany()
    @JoinTable(
        name = "deck_card",
        joinColumns = @JoinColumn(name = "deck_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id"))
    List<Card> cardsList;

    @OneToMany(mappedBy = "deck")
    List<Match> matchesList;
}
