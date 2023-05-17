import dao.AccountDao;
import dao.ProfileDao;
import dao.factory.JpaDaoFactory;
import jakarta.persistence.EntityTransaction;
import models.Account;
import models.Profile;
import types.AccountStatus;
import types.AccountType;
import types.ProfileRole;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        ProfileDao pd = JpaDaoFactory.getProfileDao();
        AccountDao ad = JpaDaoFactory.getAccountDao();
        EntityTransaction tx = JpaDaoFactory.getEntityManger().getTransaction();

        Profile np = new Profile();
        np.setLogin("12345");
        np.setPasswordHash("1235");
        np.setRole(ProfileRole.User.toSqlName());

        Account na = new Account();
        na.setStatus(AccountStatus.Open.toSqlName());
        na.setType(AccountType.Default.toSqlName());
        na.setBalance(new BigDecimal(0));
        na.setAccountNumber("123455");

        try {
            tx.begin();
            Long id = pd.create(np);

            na.setProfileId(id);
            ad.create(na);

            Account a = ad.getByAccountNumber(na.getAccountNumber());
            a.setBalance(new BigDecimal(100));
            ad.update(a);

            System.out.println(ad.getById(a.getId()));
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }

    }
}
