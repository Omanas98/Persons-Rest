package com.example.personsrest.domain;

import lombok.Data;

import java.util.List;

@Data
public class PersonImplementation implements Person{
    // getting a specific persons info and their groups
    private String id;
    private String name;
    private int age;
    private String city;
    private boolean active;
    private List<String> groupList;

    @Override
    public List<String> getGroups() {
        return null;
    }

    @Override
    public void addGroup(String groupId) { }

    @Override
    public void removeGroup(String groupId) { }

}
