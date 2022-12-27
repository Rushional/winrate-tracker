package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.models.FullDeckStatsDTO;
import com.rushional.winrate_tracker.models.repositories.MatchCardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {
    private MatchCardRepository matchCardRepository;

    public CalculationController(MatchCardRepository matchCardRepository) {
        this.matchCardRepository = matchCardRepository;
    }

//    TODO: our bottleneck is probably the database.
//     so it might be a good idea to just get the raw data from the database
//     and then compile a stats collection here, not with an SQL request.
//     This way, our request would happen much faster, releasing some of load from out database
    @GetMapping("deck_stats")
    @ResponseBody
    public FullDeckStatsDTO getDeckStats(@RequestParam Long deckId) {
        return new FullDeckStatsDTO(matchCardRepository.getDeckStats(deckId), matchCardRepository.getCardsStats(deckId));
    }
}
