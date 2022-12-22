package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.models.Card;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CardRepresentationAssembler implements RepresentationModelAssembler<Card, EntityModel<Card>> {
    @Override
    public EntityModel<Card> toModel(Card card) {

        return EntityModel.of(card, //
            linkTo(methodOn(CardController.class).one(card.getId())).withSelfRel(),
            linkTo(methodOn(CardController.class).all()).withRel("cards"));
    }
}
