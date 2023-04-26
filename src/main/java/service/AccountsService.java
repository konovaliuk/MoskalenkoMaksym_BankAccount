package main.java.service;

import main.java.dao.AccountDao;
import main.java.dao.CreditApproveDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Account;
import main.java.models.CreditApprove;
import main.java.models.Profile;
import main.java.types.AccountStatus;
import main.java.types.AccountType;
import main.java.types.ProfileRole;
import main.java.types.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class AccountsService {
    private static final AccountDao accountDao = DaoFactory.getAccountDao();
    private static final CreditApproveDao creditApprovesDao = DaoFactory.getCreditApproveDao();


    // TODO: get such data from the configs
    // One day
    private static final Long accountExpirationTime = (long) (24 * 60 * 60 * 1000);

    public static List<Account> getAccountsByProfileId(UUID profileId) {
        return accountDao.getByProfileId(profileId);
    }

    public static Account getAccountById(UUID id) {
        return accountDao.getById(id);
    }

    public static void createAccount(AccountType type, BigDecimal amount, UUID profileId) throws Exception {
        Account a = new Account(profileId, type, amount);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        switch (a.getType()) {
            case Debit -> {
                Account defaultAccount = accountDao.getDefaultAccountByProfileId(profileId);
                if (defaultAccount.getBalance().compareTo(a.getBalance()) < 0) {
                    throw new Exception("Not enough funds on default account to open debit account");
                }
                a.setStatus(AccountStatus.Open);
                a.setOpenedAt(now);
                a.setExpiredAt(new Timestamp(now.getTime() + accountExpirationTime));

                // TODO: use sql transaction
                accountDao.create(a);
                accountDao.updateBalance(defaultAccount.getId(), defaultAccount.getBalance().subtract(a.getBalance()));
                TransactionService.createTransaction(defaultAccount.getId(), a.getId(), a.getBalance(), TransactionType.Transfer);
            }
            case Credit -> {
                a.setBalance(amount.negate());
                a.setStatus(AccountStatus.Processing);
                a.setExpiredAt(new Timestamp(now.getTime() + accountExpirationTime));

                accountDao.create(a);
            }
            case Default -> {
                a.setStatus(AccountStatus.Open);
                accountDao.create(a);
            }
        }
    }

    public static Account getDefaultAccountByProfileId(UUID profileId) {
        return accountDao.getDefaultAccountByProfileId(profileId);
    }

    public static Account validateAccountForTransaction(UUID accountId) throws Exception {
        Account account = accountDao.getById(accountId);

        if (account == null) {
            throw new Exception("Account does not exist");
        }

        if (account.getStatus() != AccountStatus.Open) {
            throw new Exception("Account is not opened");
        }

        return account;
    }

    public static void closeAccount(UUID accountId) {
        accountDao.closeAccount(accountId);
    }

    public static void updateBalance(UUID accountId, BigDecimal newBalance) {
        accountDao.updateBalance(accountId, newBalance);
    }

    public static List<Account> getProcessingCreditRequests() {
        return accountDao.getProcessingCreditRequests();
    }

    public static void changeCreditApprove(UUID accountId, AccountStatus status, UUID approverId) throws Exception {
        Profile approver = ProfileService.getProfileById(approverId);

        if (approver.getRole() != ProfileRole.Admin && approver.getRole() != ProfileRole.SuperAdmin) {
            throw new Exception("User is not an admin");
        }

        Account account = accountDao.getById(accountId);

        if (account.getType() != AccountType.Credit) {
            throw new Exception("Account is not credit account");
        }

        if (account.getStatus() != AccountStatus.Processing) {
            throw new Exception("Account does not have processing status");
        }

        if (status == AccountStatus.Processing) {
            throw new Exception("Cannot set processing status");
        }

        CreditApprove creditApprove = new CreditApprove(accountId, approverId);

        switch (status) {
            case Open -> {
                accountDao.openAccount(accountId);

                Account defaultAccount = getDefaultAccountByProfileId(account.getProfileId());
                TransactionService.createTransaction(accountId, defaultAccount.getId(), account.getBalance().negate(), TransactionType.Transfer);
                AccountsService.updateBalance(defaultAccount.getId(), defaultAccount.getBalance().add(account.getBalance().negate()));
            }
            case Closed -> {
                accountDao.closeAccount(accountId);
                accountDao.updateBalance(accountId, new BigDecimal(0));
            }
        }

        creditApprovesDao.create(creditApprove);
    }
}
