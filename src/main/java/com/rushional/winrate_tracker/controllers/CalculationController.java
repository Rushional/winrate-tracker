package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.models.CardStatsDTO;
import com.rushional.winrate_tracker.models.DeckStatsDTO;
import com.rushional.winrate_tracker.models.repositories.DeckRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculationController {
    private DeckRepository deckRepository;

    public CalculationController(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

//    TODO: our bottleneck is probably the database.
//     so it might be a good idea to just get the raw data from the database
//     and then compile a stats collection here, not with an SQL request.
//     This way, our request would happen much faster, releasing some of load from out database
    @GetMapping("deck_stats")
    @ResponseBody
    public DeckStatsDTO getDeckStats(@RequestParam Long deckId) {
        return deckRepository.getDeckStats(deckId);
    }

    @GetMapping("/cards_stats")
    @ResponseBody
    public List<CardStatsDTO> getCardsStats(@RequestParam Long deckId) {
        return deckRepository.getCardsStats(deckId);
    }


//    @GetMapping("playrates")
//    @ResponseBody
//    public List<Double> getDeckCardsPlayrates(@RequestParam Long deckId) {
//        return deckRepository.getPlayrates(deckId);
//    }
//
//    @GetMapping("drawn_cube_gains")
//    @ResponseBody
//    public List<Double> getDrawnCubeGains(@RequestParam Long deckId) {
//        return deckRepository.getDrawnCubeGains(deckId);
//    }
//
//    @GetMapping("played_cube_gains")
//    @ResponseBody
//    public List<Double> getPlayedCubeGains(@RequestParam Long deckId) {
//        return deckRepository.getPlayedCubeGains(deckId);
//    }
//
//    @GetMapping("drawn_winrates")
//    @ResponseBody
//    public List<Double> getDrawnWinrates(@RequestParam Long deckId) {
//        return deckRepository.getDrawnWinrates(deckId);
//    }
//
//    @GetMapping("played_winrates")
//    @ResponseBody
//    public List<Double> getPlayedWinrates(@RequestParam Long deckId) {
//        return deckRepository.getPlayedWinrates(deckId);
//    }

//    @GetMapping("avg_cube_gain")
//    public String getDeckAverageCubeGain(@RequestParam Long deckId) {
//        return Double.toString(deckRepository.getAverageCubeGain(deckId));
//    }
//
//    @GetMapping("winrate")
//    public String getDeckWinrate(@RequestParam Long deckId) {
//        return Double.toString(deckRepository.getWinrate(deckId));
//    }
//
//    @GetMapping("total_cubes")
//    public String getTotalCubes(@RequestParam Long deckId) {
//        return Double.toString(deckRepository.getCubesTotal(deckId));
//    }
//
//
//    @GetMapping("matches_amount")
//    public String getMatchesAmount(@RequestParam Long deckId) {
//        return Double.toString(deckRepository.getMatchesAmount(deckId));
//    }
//
//    @GetMapping("/missed_snaps_amount")
//    public String getMissedSnapsAmount(@RequestParam Long deckId)  {
//        return Double.toString(deckRepository.getMissedSnapsAmount(deckId));
//    }
//
//    @GetMapping("/loosing_snaps_amount")
//    public String getLoosingSnapsAmount(@RequestParam Long deckId) {
//        return Double.toString(deckRepository.getLoosingSnapsAmount(deckId));
//    }
}
