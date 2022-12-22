package com.rushional.winrate_tracker.controllers.exceptions;

public class DeckNotFoundException extends RuntimeException {
    public DeckNotFoundException(Long id) {
        super("Could not find deck " + id);
    }
}
