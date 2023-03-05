import models.profile.Profile;
import models.profile.ProfileDaoImpl;
import models.profile.ProfileRole;

public class Main {
    public static void main(String[] args) {
        ProfileDaoImpl profileDao = new ProfileDaoImpl();

        Profile p = new Profile("login_1", "lets_imagine_that_its_a_password_hash");
        profileDao.create(p);

        profileDao.promote(p.getId(), ProfileRole.SuperAdmin);

        Profile p2 = profileDao.getById(p.getId());

        p2.print();
    }
}