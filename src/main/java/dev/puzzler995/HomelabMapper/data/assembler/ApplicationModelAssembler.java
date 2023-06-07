package dev.puzzler995.HomelabMapper.data.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;
import dev.puzzler995.HomelabMapper.data.model.Application;
import dev.puzzler995.HomelabMapper.data.controller.ApplicationController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ApplicationModelAssembler implements RepresentationModelAssembler<Application, EntityModel<Application>> {
    @Override
    public EntityModel<Application> toModel(Application application) {
        return EntityModel.of(application,
            linkTo(methodOn(ApplicationController.class).one(application.getId())).withSelfRel(),
            linkTo(methodOn(ApplicationController.class).all()).withRel("applications"));
    }
}
