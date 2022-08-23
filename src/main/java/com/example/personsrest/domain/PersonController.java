package com.example.personsrest.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<PersonDTO> getPerson(@PathVariable("id") String id){
        return personService.getPerson(id).map(this::toDTO);
    }

    @PostMapping
    public PersonDTO createPerson(@RequestBody CreatePerson createPerson){
        return toDTO(personService.createPerson(createPerson));
    }

    private PersonDTO toDTO(Person person){
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups());
    }

}
