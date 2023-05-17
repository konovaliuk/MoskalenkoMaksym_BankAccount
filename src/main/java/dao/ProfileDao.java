package dao;

import models.Profile;


public interface ProfileDao {
    Long create(Profile p);

    void promote(Long id, String role);

    Profile getById(Long id);

    Profile getByLogin(String login);

}
