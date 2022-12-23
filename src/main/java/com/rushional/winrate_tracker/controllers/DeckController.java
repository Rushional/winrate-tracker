package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.controllers.exceptions.DeckNotFoundException;
import com.rushional.winrate_tracker.models.entities.Deck;
import com.rushional.winrate_tracker.models.repositories.DeckRepository;
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
public class DeckController {

    private final DeckRepository repository;
    private final DeckRepresentationAssembler assembler;

    public DeckController(DeckRepository repository, DeckRepresentationAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/decks")
    CollectionModel<EntityModel<Deck>> all() {
        List<EntityModel<Deck>> decks = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(decks, linkTo(methodOn(DeckController.class).all()).withSelfRel());
    }

    @GetMapping("/decks/{id}")
    EntityModel<Deck> one(@PathVariable Long id) {
        Deck card = repository.findById(id)
            .orElseThrow(() -> new DeckNotFoundException(id));
        return assembler.toModel(card);
    }

    @PostMapping("/decks")
    ResponseEntity<?> newDeck(@RequestBody Deck newDeck) {
        EntityModel<Deck> entityModel = assembler.toModel(repository.save(newDeck));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

//    TODO: I will probably need to create edit and delete methods eventually
}
