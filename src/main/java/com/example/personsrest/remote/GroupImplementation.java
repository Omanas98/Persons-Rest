package com.example.personsrest.remote;

import lombok.Value;

@Value
public class GroupImplementation implements GroupRemote {
    @Override
    public String getNameById(String groupId) {
        return null;
    }

    @Override
    public String createGroup(String name) {
        return null;
    }

    @Override
    public String removeGroup(String groupId) {
        return null;
    }
}
