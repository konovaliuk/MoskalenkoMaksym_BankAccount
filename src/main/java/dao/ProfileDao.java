package main.java.dao;

import main.java.models.Profile;
import main.java.types.ProfileRole;

import java.util.UUID;

public interface ProfileDao {
    void create(Profile p);

    void promote(UUID id, ProfileRole role);

    Profile getById(UUID id);

}
