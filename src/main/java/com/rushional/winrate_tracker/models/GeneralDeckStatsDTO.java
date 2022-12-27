package com.rushional.winrate_tracker.models;

import lombok.Getter;

@Getter
public class GeneralDeckStatsDTO {
    private Double averageCubeGain;
    private Double winrate;
    private Long totalCubes;
    private Long matchesAmount;
    private Double missedSnaps;
    private Double loosingSnaps;

    public GeneralDeckStatsDTO(Double averageCubeGain, Double winrate, Long totalCubes,
                               Long matchesAmount, Double missedSnaps, Double loosingSnaps) {
        this.averageCubeGain = averageCubeGain;
        this.winrate = winrate;
        this.totalCubes = totalCubes;
        this.matchesAmount = matchesAmount;
        this.missedSnaps = missedSnaps;
        this.loosingSnaps = loosingSnaps;
    }
}
