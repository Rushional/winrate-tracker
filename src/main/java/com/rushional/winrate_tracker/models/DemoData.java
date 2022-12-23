package com.rushional.winrate_tracker.models;

import com.rushional.winrate_tracker.models.entities.Card;
import com.rushional.winrate_tracker.models.entities.Deck;
import com.rushional.winrate_tracker.models.entities.Match;
import com.rushional.winrate_tracker.models.entities.MatchCard;
import com.rushional.winrate_tracker.models.repositories.CardRepository;
import com.rushional.winrate_tracker.models.repositories.DeckRepository;
import com.rushional.winrate_tracker.models.repositories.MatchCardRepository;
import com.rushional.winrate_tracker.models.repositories.MatchRepository;
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


//        ******** MATCH 1.1 ********
        Match match11 = new Match(deck1, 8, 6, true,
            true, true, "ez 8 cube bot game");
        matchRepo.save(match11);

        MatchCard matchCard11Hood = new MatchCard();
        matchCardRepo.save(matchCard11Hood);
        matchCard11Hood.setMatch(match11);
        matchCard11Hood.setCard(cardTheHood);
        matchCard11Hood.setDrawn(true);
        matchCard11Hood.setPlayed(true);
        matchCardRepo.save(matchCard11Hood);

        MatchCard matchCard11Carnage = new MatchCard();
        matchCardRepo.save(matchCard11Carnage);
        matchCard11Carnage.setMatch(match11);
        matchCard11Carnage.setCard(cardCarnage);
        matchCard11Carnage.setDrawn(false);
        matchCard11Carnage.setPlayed(false);
        matchCardRepo.save(matchCard11Carnage);

        matchCardRepo.save(matchCard11Hood);
        matchRepo.save(match11);
        matchCardRepo.save(matchCard11Carnage);
        matchRepo.save(match11);

//        ******** MATCH 1.2 ********
        Match match12 = new Match(deck1, -4, 6, true,
            false, false, "a tragedy");
        matchRepo.save(match12);

        MatchCard matchCard12Hood = new MatchCard();
        matchCardRepo.save(matchCard12Hood);
        matchCard12Hood.setMatch(match12);
        matchCard12Hood.setCard(cardTheHood);
        matchCard12Hood.setDrawn(true);
        matchCard12Hood.setPlayed(true);
        matchCardRepo.save(matchCard12Hood);

        MatchCard matchCard12Carnage = new MatchCard();
        matchCardRepo.save(matchCard12Carnage);
        matchCard12Carnage.setMatch(match12);
        matchCard12Carnage.setCard(cardCarnage);
        matchCard12Carnage.setDrawn(false);
        matchCard12Carnage.setPlayed(false);
        matchCardRepo.save(matchCard12Carnage);

        matchCardRepo.save(matchCard12Hood);
        matchRepo.save(match12);
        matchCardRepo.save(matchCard12Carnage);
        matchRepo.save(match12);
//        ******** MATCH 1.3 ********
        Match match13 = new Match(deck1, -1, 5, false,
            true, false, "wise retreat");
        matchRepo.save(match13);

        MatchCard matchCard13Hood = new MatchCard();
        matchCardRepo.save(matchCard13Hood);
        matchCard13Hood.setMatch(match13);
        matchCard13Hood.setCard(cardTheHood);
        matchCard13Hood.setDrawn(true);
        matchCard13Hood.setPlayed(true);
        matchCardRepo.save(matchCard13Hood);

        MatchCard matchCard13Carnage = new MatchCard();
        matchCardRepo.save(matchCard13Carnage);
        matchCard13Carnage.setMatch(match13);
        matchCard13Carnage.setCard(cardCarnage);
        matchCard13Carnage.setDrawn(false);
        matchCard13Carnage.setPlayed(false);
        matchCardRepo.save(matchCard13Carnage);

        matchCardRepo.save(matchCard13Hood);
        matchRepo.save(match13);
        matchCardRepo.save(matchCard13Carnage);
        matchRepo.save(match13);

//        ******** MATCH 1.4 ********
        Match match14 = new Match(deck1, 4, 5, true,
            true, false, "opponent retreated after snapping:3");
        matchRepo.save(match14);

        MatchCard matchCard14Hood = new MatchCard();
        matchCardRepo.save(matchCard14Hood);
        matchCard14Hood.setMatch(match14);
        matchCard14Hood.setCard(cardTheHood);
        matchCard14Hood.setDrawn(true);
        matchCard14Hood.setPlayed(true);
        matchCardRepo.save(matchCard14Hood);

        MatchCard matchCard14Carnage = new MatchCard();
        matchCardRepo.save(matchCard14Carnage);
        matchCard14Carnage.setMatch(match14);
        matchCard14Carnage.setCard(cardCarnage);
        matchCard14Carnage.setDrawn(false);
        matchCard14Carnage.setPlayed(false);
        matchCardRepo.save(matchCard14Carnage);

        matchCardRepo.save(matchCard14Hood);
        matchRepo.save(match14);
        matchCardRepo.save(matchCard14Carnage);
        matchRepo.save(match14);

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
