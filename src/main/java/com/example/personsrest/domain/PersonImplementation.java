package com.example.personsrest.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PersonImplementation implements Person{
    private String id;
    private String name;
    private int age;
    private String city;
    private boolean active;
    private List<String> groupList;

    @Override
    public List<String> getGroups() {
        return new ArrayList<>(groupList);
    }

    @Override
    public void addGroup(String groupId) { }

    @Override
    public void removeGroup(String groupId) { }

}
