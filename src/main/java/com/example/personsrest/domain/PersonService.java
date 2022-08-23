package com.example.personsrest.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    // Fetching persons, by using the findAll() method in personRepository
    private PersonRepository personRepository;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(String id) {
        return personRepository.findById(id);
    }

    public Person createPerson(CreatePerson createPerson) {
        PersonImplementation person = new PersonImplementation();
        return personRepository.save(person);
    }
}
