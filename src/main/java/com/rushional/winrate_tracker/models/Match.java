package com.rushional.winrate_tracker.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
public class Match {

    public Match() {}

    public Match(
        Deck deck, int cubeGain, int turnsPlayed, boolean userSnapped,
        boolean opponentSnapped, boolean isBot, String notes) {
//        TODO: I tried to comment this out, hoping it will still work
        //        cardsAssociation = new ArrayList<>();
        this.deck = deck;
        this.cubeGain = cubeGain;
        this.turnsPlayed = turnsPlayed;
        this.userSnapped = userSnapped;
        this.opponentSnapped = opponentSnapped;
        this.isOpponentBot = isBot;
        this.notes = notes;
    }


    @Id
    @GeneratedValue
    private Long id;

//    @ManyToMany(
//        cascade = {
//            CascadeType.PERSIST
//        }
//    )
    @OneToMany( mappedBy = "match")
//    @JoinTable(
//        name = "match_card",
//        joinColumns = @JoinColumn(name = "match_id"),
//        inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<MatchCard> cardsAssociation;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Deck deck;

    private int cubeGain;
    private int turnsPlayed;
    boolean userSnapped;
    boolean opponentSnapped;
    boolean isOpponentBot;
    String notes;
}
