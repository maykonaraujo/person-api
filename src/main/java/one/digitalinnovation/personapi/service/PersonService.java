package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.controller.PersonController;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.response.PersonModelAssembler;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    private PersonRepository repository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    private final PersonModelAssembler personModelAssembler;


    @Autowired
    public PersonService(PersonRepository repository, PersonModelAssembler personModelAssembler) {
        this.repository = repository;
        this.personModelAssembler = personModelAssembler;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = repository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created Person with ID ");

    }

    public CollectionModel<EntityModel<Person>> listAll() throws PersonNotFoundException {
        List<EntityModel<Person>> allPeople = repository.findAll()
                .stream()
                .map(personModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(allPeople, linkTo(methodOn(PersonController.class).listAll())
                .withSelfRel());
    }

    public EntityModel<Person> findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personModelAssembler.toModel(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = repository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Update Person with ID ");

    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
