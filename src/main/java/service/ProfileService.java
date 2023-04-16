package main.java.service;

import main.java.dao.ProfileDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Profile;
import main.java.utils.HashUtil;

public class ProfileService {
    ProfileDao profileDao = DaoFactory.getProfileDao();

    public Profile signUp(String login, String password) {
        Profile p = profileDao.getByLogin(login);
        if (p == null) {
            p = new Profile(login, HashUtil.sha256(password));
            profileDao.create(p);

            return p;
        }

        return null;
    }

    public Profile signIn(String login, String password) {
        Profile p = profileDao.getByLogin(login);
        if (p != null && HashUtil.sha256(password).equals(p.getPasswordHash())) {
            return p;
        }
        return null;
    }
}
