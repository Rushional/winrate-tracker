package com.rushional.winrate_tracker.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DemoData {
    @Autowired
    private CardRepository cardRepo;

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private MatchRepository matchRepo;

    @Autowired
    private MatchCardRepository matchCardRepo;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Card cardTheHood = new Card("The Hood");
        cardRepo.save(cardTheHood);
        Card cardCarnage = new Card("Carnage");
        cardRepo.save(cardCarnage);
        Card cardViper = new Card("Viper");
        cardRepo.save(cardViper);

        Deck deck1 = new Deck("deck1 deck code", "destruction deck");
        deck1.getCardsList().add(cardTheHood);
        deck1.getCardsList().add(cardCarnage);
        deckRepo.save(deck1);

        Deck deck2 = new Deck("deck2 deck code", "control deck");
        deck2.getCardsList().add(cardTheHood);
        deck2.getCardsList().add(cardViper);
        deckRepo.save(deck2);

        Match match11 = new Match(deck1, 8, 6, true,
            true, true, "ez 8 cube bot game");
        matchRepo.save(match11);

        MatchCard matchCard11Hood = new MatchCard();
        matchCardRepo.save(matchCard11Hood);
//        MatchCard matchCard11Hood = new MatchCard(match11, cardTheHood, true, true);
        matchCard11Hood.setMatch(match11);
        matchCard11Hood.setCard(cardTheHood);
        matchCard11Hood.setDrawn(true);
        matchCard11Hood.setPlayed(true);
        matchCardRepo.save(matchCard11Hood);

        MatchCard matchCard11Carnage = new MatchCard();
        matchCardRepo.save(matchCard11Carnage);
//        MatchCard matchCard11Carnage = new MatchCard(match11, cardCarnage, false, false);
        matchCard11Carnage.setMatch(match11);
        matchCard11Carnage.setCard(cardCarnage);
        matchCard11Carnage.setDrawn(false);
        matchCard11Carnage.setPlayed(false);
        matchCardRepo.save(matchCard11Carnage);

//        match11.getCardsList().add(cardTheHood);
        matchCardRepo.save(matchCard11Hood);
        matchRepo.save(match11);
//        match11.getCardsList().add(cardCarnage);
        matchCardRepo.save(matchCard11Carnage);
        matchRepo.save(match11);

//        Match match12 = new Match(deck1, -1, 5, false, true, false, null);
//        MatchCard matchCard12Hood = new MatchCard(match11, cardTheHood, true, false);
//        MatchCard matchCard12Carnage = new MatchCard(match11, cardCarnage, true, false);
//        match12.getCardsList().add(cardTheHood);
//        match12.getCardsList().add(cardCarnage);
//        matchRepo.save(match12);
//        matchCardRepo.save(matchCard12Hood);
//        matchCardRepo.save(matchCard12Carnage);

//        TODO: add a couple of matches w/ deck2
    }
}
