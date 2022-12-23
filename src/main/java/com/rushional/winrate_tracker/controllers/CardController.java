package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.controllers.exceptions.CardNotFoundException;
import com.rushional.winrate_tracker.models.entities.Card;
import com.rushional.winrate_tracker.models.repositories.CardRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CardController {
    private final CardRepository repository;
    private final CardRepresentationAssembler assembler;

    public CardController(CardRepository repository, CardRepresentationAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/cards")
    CollectionModel<EntityModel<Card>> all() {
        List<EntityModel<Card>> cards = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(cards, linkTo(methodOn(CardController.class).all()).withSelfRel());
    }

    @GetMapping("/cards/{id}")
    EntityModel<Card> one(@PathVariable Long id) {
        Card card = repository.findById(id)
            .orElseThrow(() -> new CardNotFoundException(id));
        return assembler.toModel(card);
    }

    @PostMapping("/cards")
    ResponseEntity<?> newCard(@RequestBody Card newCard) {
        EntityModel<Card> entityModel = assembler.toModel(repository.save(newCard));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

//    TODO: I will probably need to create edit and delete methods eventually

}
