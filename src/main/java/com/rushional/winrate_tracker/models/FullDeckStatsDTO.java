package com.rushional.winrate_tracker.models;

import lombok.Getter;

import java.util.List;

@Getter
public class FullDeckStatsDTO {
    private final GeneralDeckStatsDTO generalDeckStats;
    private final List<CardStatsDTO> listCardStats;

    public FullDeckStatsDTO(GeneralDeckStatsDTO generalDeckStats, List<CardStatsDTO> listCardStats) {
        this.generalDeckStats = generalDeckStats;
        this.listCardStats = listCardStats;
    }
}
