package dao.factory;

import dao.*;
import dao.impl.AccountRateDaoImpl;
import dao.impl.CreditApproveDaoImpl;
import dao.impl.TransactionDaoImpl;
import dao.jpa.AccountDaoImpl;
import dao.jpa.ProfileDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaDaoFactory {
    private static final EntityManager entityManager = Persistence.createEntityManagerFactory("BankAccounts").createEntityManager();

    private static final AccountDao accountDao = new AccountDaoImpl(entityManager);
    private static final AccountRateDao accountRateDao = new AccountRateDaoImpl();
    private static final CreditApproveDao creditApproveDao = new CreditApproveDaoImpl();
    private static final ProfileDao profileDao = new ProfileDaoImpl(entityManager);
    private static final TransactionDao transactionDao = new TransactionDaoImpl();

    public static AccountDao getAccountDao() {
        return accountDao;
    }

    public static AccountRateDao getAccountRateDao() {
        return accountRateDao;
    }

    public static CreditApproveDao getCreditApproveDao() {
        return creditApproveDao;
    }

    public static ProfileDao getProfileDao() {
        return profileDao;
    }

    public static TransactionDao getTransactionDao() {
        return transactionDao;
    }

    public static EntityManager getEntityManger() {
        return entityManager;
    }
}

