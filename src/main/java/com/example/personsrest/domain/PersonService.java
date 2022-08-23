package com.example.personsrest.domain;

import com.example.personsrest.remote.GroupImplementation;
import com.example.personsrest.remote.GroupRemote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;
    private GroupRemote groupRemote;

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

    public Person updatePerson(String id, String name, String city, int age) {
        Person person = personRepository.findById(id).get();
        person.setName(name);
        person.setCity(city);
        person.setAge(age);
        return personRepository.save(person);
    }

    public void deletePersonId(String id) {
        personRepository.delete(id);
    }

}
