package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.models.Card;
import com.rushional.winrate_tracker.models.Deck;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeckRepresentationAssembler implements RepresentationModelAssembler<Deck, EntityModel<Deck>> {
    @Override
    public EntityModel<Deck> toModel(Deck deck) {

        return EntityModel.of(deck, //
            linkTo(methodOn(DeckController.class).one(deck.getId())).withSelfRel(),
            linkTo(methodOn(DeckController.class).all()).withRel("decks"));
    }
}
