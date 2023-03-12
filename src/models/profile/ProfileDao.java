package models.profile;

import java.util.UUID;

public interface ProfileDao {
    void create(Profile p);

    void promote(UUID id, ProfileRole role);

    Profile getById(UUID id);

}
