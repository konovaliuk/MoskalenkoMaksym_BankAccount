import db.DatabaseConnection;
import models.profile.Profile;
import models.profile.ProfileDaoImpl;
import models.profile.ProfileRole;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Profile p = new Profile("login_1", "lets_imagine_that_its_a_password_hash");
        ProfileDaoImpl.create(p);

        p.setRole(ProfileRole.SuperAdmin);
        ProfileDaoImpl.updateById(p);

        Profile p2 = ProfileDaoImpl.getById(p.getId());

        p2.print();
    }
}