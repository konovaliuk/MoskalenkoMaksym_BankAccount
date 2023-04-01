package main.java;

import main.java.models.Profile;
import main.java.dao.impl.ProfileDaoImpl;
import main.java.types.ProfileRole;

public class Main {
    public static void main(String[] args) {
        ProfileDaoImpl profileDao = new ProfileDaoImpl();


        Profile p = new Profile("login_" + (int)(Math.random()*1_000_000), "lets_imagine_that_its_a_password_hash");
        profileDao.create(p);

        profileDao.promote(p.getId(), ProfileRole.SuperAdmin);

        Profile p2 = profileDao.getById(p.getId());

        p2.print();
    }
}