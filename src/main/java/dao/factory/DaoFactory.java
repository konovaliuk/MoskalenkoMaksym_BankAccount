package dao.factory;

import dao.*;
import dao.impl.*;

public class DaoFactory {
    private static final AccountDao accountDao = new AccountDaoImpl();
    private static final AccountRateDao accountRateDao = new AccountRateDaoImpl();
    private static final CreditApproveDao creditApproveDao = new CreditApproveDaoImpl();
    private static final ProfileDao profileDao = new ProfileDaoImpl();
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
}

