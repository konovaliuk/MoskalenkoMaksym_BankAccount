package models.profile;

import java.util.UUID;

public interface ProfileDao {
    public static void create(Profile p) {};
    public static void updateById(Profile p) {};
    public static Profile getById(UUID id) {return null;};
}
