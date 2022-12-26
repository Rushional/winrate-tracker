package com.rushional.winrate_tracker.models;

import lombok.Getter;

@Getter
public class CardStatsDTO {
    private Long cardId;

    public CardStatsDTO(Long cardId, String name, Double playrate, Double drawnCubeGain,
                        Double playedCubeGain, Double drawnWinrate, Double playedWinrate) {
        this.cardId = cardId;
        this.name = name;
        this.playrate = playrate;
        this.drawnCubeGain = drawnCubeGain;
        this.playedCubeGain = playedCubeGain;
        this.drawnWinrate = drawnWinrate;
        this.playedWinrate = playedWinrate;
    }

    private String name;
    private Double playrate;
    private Double drawnCubeGain;
    private Double playedCubeGain;
    private Double drawnWinrate;
    private Double playedWinrate;
}
