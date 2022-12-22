package com.rushional.winrate_tracker.controllers;

import com.rushional.winrate_tracker.controllers.exceptions.MatchNotFoundException;
import com.rushional.winrate_tracker.models.Match;
import com.rushional.winrate_tracker.models.MatchRepository;
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
public class MatchController {
    private final MatchRepository repository;
    private final MatchRepresentationAssembler assembler;

    public MatchController(MatchRepository repository, MatchRepresentationAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/matches")
    CollectionModel<EntityModel<Match>> all() {
        List<EntityModel<Match>> matches = repository.findAll().stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(matches, linkTo(methodOn(MatchController.class).all()).withSelfRel());
    }

    @GetMapping("/matches/{id}")
    EntityModel<Match> one(@PathVariable Long id) {
        Match match = repository.findById(id)
            .orElseThrow(() -> new MatchNotFoundException(id));
        return assembler.toModel(match);
    }

    @PostMapping("/matches")
    ResponseEntity<?> newMatch(@RequestBody Match newMatch) {
        EntityModel<Match> entityModel = assembler.toModel(repository.save(newMatch));

        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

//    TODO: I will probably need to create edit and delete methods eventually
}
