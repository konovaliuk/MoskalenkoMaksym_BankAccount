package service;

import dao.ProfileDao;
import dao.factory.DaoFactory;
import models.Profile;
import utils.HashUtil;


public class ProfileService {
    private static final ProfileDao profileDao = DaoFactory.getProfileDao();

    public static Profile signUp(String login, String password) {
        Profile p = profileDao.getByLogin(login);
        if (p == null) {
            p = new Profile(login, HashUtil.sha256(password));
            Long id = profileDao.create(p);

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

    public static Profile getProfileById(Long id) {
        return profileDao.getById(id);
    }
}
