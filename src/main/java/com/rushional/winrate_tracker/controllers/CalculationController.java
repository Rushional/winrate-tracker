package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.models.DeckStatsDTO;
import com.rushional.winrate_tracker.models.repositories.DeckRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {
    private DeckRepository deckRepository;

    public CalculationController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    @GetMapping("deck_stats")
    @ResponseBody
    public DeckStatsDTO getDeckStats(@RequestParam Long deckId) {
        return deckRepository.getDeckStats(deckId);
    }

    @GetMapping("avg_cube_gain")
    public String getDeckAverageCubeGain(@RequestParam Long deckId) {
        return Double.toString(deckRepository.getAverageCubeGain(deckId));
    }

    @GetMapping("winrate")
    public String getDeckWinrate(@RequestParam Long deckId) {
        return Double.toString(deckRepository.getWinrate(deckId));
    }

    @GetMapping("total_cubes")
    public String getTotalCubes(@RequestParam Long deckId) {
        return Double.toString(deckRepository.getCubesTotal(deckId));
    }


    @GetMapping("matches_amount")
    public String getMatchesAmount(@RequestParam Long deckId) {
        return Double.toString(deckRepository.getMatchesAmount(deckId));
    }

    @GetMapping("/missed_snaps_amount")
    public String getMissedSnapsAmount(@RequestParam Long deckId)  {
        return Double.toString(deckRepository.getMissedSnapsAmount(deckId));
    }

    @GetMapping("/loosing_snaps_amount")
    public String getLoosingSnapsAmount(@RequestParam Long deckId) {
        return Double.toString(deckRepository.getLoosingSnapsAmount(deckId));
    }
}
