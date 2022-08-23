package com.example.personsrest.domain;

import com.example.personsrest.remote.GroupImplementation;
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
    public Optional<PersonDTO> getPerson(@PathVariable("id") String id) {
        return personService.getPerson(id).map(this::toDTO);
    }

    @PostMapping
    public PersonDTO createPerson(@RequestBody CreatePerson createPerson){
        PersonImplementation person = new PersonImplementation();
        return toDTO(personService.createPerson(createPerson));
    }

    @PutMapping("/{id}")
    public PersonDTO updatePerson(@PathVariable("id") String id, @RequestBody UpdatePerson updatePerson) {
        return toDTO(personService.updatePerson(
            id,
            updatePerson.getName(),
            updatePerson.getCity(),
            updatePerson.getAge()
        ));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable("id") String id) {
        personService.deletePersonId(id);
    }

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups()
        );
    }

}
