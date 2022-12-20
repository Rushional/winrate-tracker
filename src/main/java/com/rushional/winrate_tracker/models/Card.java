package com.rushional.winrate_tracker.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    TODO: not sure if we actually need this code for MtM to work
    @ManyToMany(mappedBy = "cardsList")
    List<Deck> decksList;

    @ManyToMany(mappedBy = "cardsList")
    List<Match> matchesList;
}
