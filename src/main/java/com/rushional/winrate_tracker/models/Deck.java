package com.rushional.winrate_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Deck {
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
}
