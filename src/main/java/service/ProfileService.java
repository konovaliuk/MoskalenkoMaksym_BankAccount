package main.java.service;

import main.java.dao.ProfileDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Profile;
import main.java.utils.HashUtil;

import java.util.UUID;

public class ProfileService {
    private static final ProfileDao profileDao = DaoFactory.getProfileDao();

    public static Profile signUp(String login, String password) {
        Profile p = profileDao.getByLogin(login);
        if (p == null) {
            p = new Profile(login, HashUtil.sha256(password));
            UUID id = profileDao.create(p);

            p.setId(id);

            return p;
        }

        return null;
    }

    public static Profile signIn(String login, String password) {
        Profile p = profileDao.getByLogin(login);
        if (p != null && HashUtil.sha256(password).equals(p.getPasswordHash())) {
            return p;
        }
        return null;
    }

    public static Profile getProfileById(UUID id) {
        return profileDao.getById(id);
    }
}
