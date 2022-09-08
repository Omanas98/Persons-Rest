package com.example.personsrest.domain;

import com.example.personsrest.remote.GroupRemote;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class PersonImplementation implements Person {
    private String id;
    private String name;
    private int age;
    private String city;
    private boolean active;
    private List<String> groupList;

    private GroupRemote groupRemote;

    public PersonImplementation(String name, String city, int age, ArrayList<String> groupList) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.city = city;
        this.age = age;
        this.groupList = groupList;
    }

    @Override
    public List<String> getGroups() {
        return this.groupList == null ? List.of() : this.groupList;
    }

    @Override
    public void addGroup(String groupId) {
        this.groupList.add(groupId);
    }

    @Override
    public void removeGroup(String groupId) {
        this.groupList.removeIf(g -> g.equals(groupId));
    }
}
