package one.digitalinnovation.personapi.dto.response;

import lombok.SneakyThrows;
import one.digitalinnovation.personapi.controller.PersonController;
import one.digitalinnovation.personapi.entity.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @SneakyThrows
    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).findById(person.getId())).withRel("PUT"),
                linkTo(methodOn(PersonController.class).findById(person.getId())).withRel("DELETE"),
                linkTo(methodOn(PersonController.class).findById(person.getId())).withRel("PATCH"),
                linkTo(methodOn(PersonController.class).listAll()).withRel("GET"));
    }
}
