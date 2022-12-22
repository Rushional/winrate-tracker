package com.rushional.winrate_tracker.controllers.exceptions;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(Long id) {
        super("Could not find match " + id);
    }
}
