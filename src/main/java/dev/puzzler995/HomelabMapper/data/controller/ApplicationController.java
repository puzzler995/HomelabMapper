package dev.puzzler995.HomelabMapper.data.controller;

import dev.puzzler995.HomelabMapper.data.assembler.ApplicationModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import dev.puzzler995.HomelabMapper.data.repository.ApplicationRepository;
import dev.puzzler995.HomelabMapper.data.model.Application;
import java.util.List;
import java.util.stream.Collectors;

import dev.puzzler995.HomelabMapper.data.exception.ApplicationNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class ApplicationController {
    private final ApplicationRepository repository;
    private final ApplicationModelAssembler assembler;

    ApplicationController(ApplicationRepository repository, ApplicationModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/applications")
    public CollectionModel<EntityModel<Application>> all() {
        List<EntityModel<Application>> applications = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(applications,
                linkTo(methodOn(ApplicationController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/applications")
    ResponseEntity<?> newApplication(@RequestBody Application newApplication) {
        EntityModel<Application> entityModel = assembler.toModel(repository.save(newApplication));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // Single item

    @GetMapping("/applications/{id}")
    public EntityModel<Application> one(@PathVariable Long id) {
        Application application = repository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException(id));

        return assembler.toModel(application);
    }

    @PutMapping("/applications/{id}")
    Application replaceApplication(@RequestBody Application newApplication, @PathVariable Long id) {
        return repository.findById(id)
                .map(application -> {
                    application.setName(newApplication.getName());
                    application.setDescription(newApplication.getDescription());
                    application.setUrl(newApplication.getUrl());
                    application.setIcon(newApplication.getIcon());
                    return repository.save(application);
                })
                .orElseGet(() -> {
                    newApplication.setId(id);
                    return repository.save(newApplication);
                });
    }

    @DeleteMapping("/applications/{id}")
    void deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
