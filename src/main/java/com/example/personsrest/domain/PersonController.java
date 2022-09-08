package com.example.personsrest.domain;

import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;
    private GroupRemote groupRemote;

    @GetMapping
    public List<PersonDTO> getAllPersons(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "pagenumber", required = false) Integer pageNumber,
            @RequestParam(name = "pagesize", required = false) Integer pageSize) {
        if(search == null || search.equals("")){
            return personService.getAllPersons().stream().map(this::toDTO).collect(Collectors.toList());
        }
        Page<Person> page = personService.getAllNamesAndCities(search, pageNumber, pageSize);
        List<Person> list = page.getContent();
        return list.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<PersonDTO> getPerson(@PathVariable("id") String id) {
        return personService.getPerson(id).map(this::toDTO);
    }

    @PostMapping
    public PersonDTO createPerson(@RequestBody CreatePerson createPerson) {
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

    @PutMapping("/{id}/addGroup/{groupName}")
    public PersonDTO addGroupToPerson(@PathVariable("id") String id,
                                      @PathVariable("groupName") String groupName) {
        return toDTO(personService.addGroupPerson(id, groupName));
    }

    @DeleteMapping("/{id}/removeGroup/{groupId}")
    public PersonDTO removeGroup(@PathVariable("id") String id,
                                 @PathVariable("groupId") String groupId) {
        return toDTO(personService.removeGroupFromPerson(
                id,
                groupId
        ));

    }

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getCity(),
                person.getAge(),
                person.getGroups().stream().map(name -> groupRemote.getNameById(name)).collect(Collectors.toList()));
    }
}
