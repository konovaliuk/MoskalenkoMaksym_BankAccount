package service;

import dao.AccountDao;
import dao.CreditApproveDao;
import dao.factory.DaoFactory;
import models.Account;
import models.CreditApprove;
import models.Profile;
import types.AccountStatus;
import types.AccountType;
import types.ProfileRole;
import types.TransactionType;
import utils.RandomUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


public class AccountsService {
    private static final AccountDao accountDao = DaoFactory.getAccountDao();
    private static final CreditApproveDao creditApprovesDao = DaoFactory.getCreditApproveDao();


    // TODO: get such data from the configs
    // One day
    private static final Long accountExpirationTime = (long) (24 * 60 * 60 * 1000);

    public static List<Account> getAccountsByProfileId(Long profileId) {
        return accountDao.getByProfileId(profileId);
    }

    public static Account getAccountById(Long id) {
        return accountDao.getById(id);
    }

    public static void createAccount(AccountType type, BigDecimal amount, Long profileId) throws Exception {
        Account a = new Account(profileId, type.toSqlName(), amount, RandomUtil.getRandomString(16, "0123456789"));
        Timestamp now = new Timestamp(System.currentTimeMillis());

        switch (AccountType.fromSqlName(a.getType())) {
            case Debit -> {
                Account defaultAccount = accountDao.getDefaultAccountByProfileId(profileId);
                if (defaultAccount.getBalance().compareTo(a.getBalance()) < 0) {
                    throw new Exception("Not enough funds on default account to open debit account");
                }
                a.setStatus(AccountStatus.Open.toSqlName());
                a.setOpenedAt(now);
                a.setExpiredAt(new Timestamp(now.getTime() + accountExpirationTime));

                // TODO: use sql transaction
                accountDao.create(a);
                accountDao.updateBalance(defaultAccount.getId(), defaultAccount.getBalance().subtract(a.getBalance()));
                TransactionService.createTransaction(defaultAccount.getId(), a.getId(), a.getBalance(), TransactionType.Transfer);
            }
            case Credit -> {
                a.setBalance(amount.negate());
                a.setStatus(AccountStatus.Processing.toSqlName());
                a.setExpiredAt(new Timestamp(now.getTime() + accountExpirationTime));

                accountDao.create(a);
            }
            case Default -> {
                a.setStatus(AccountStatus.Open.toSqlName());
                accountDao.create(a);
            }
        }
    }

    public static Account getDefaultAccountByProfileId(Long profileId) {
        return accountDao.getDefaultAccountByProfileId(profileId);
    }

    public static Account validateAccountForTransaction(String accountNumber) throws Exception {
        Account account = accountDao.getByAccountNumber(accountNumber);

        if (account == null) {
            throw new Exception("Account does not exist");
        }

        if (!Objects.equals(account.getStatus(), AccountStatus.Open.toSqlName())) {
            throw new Exception("Account is not opened");
        }

        return account;
    }

    public static void closeAccount(Long accountId) {
        accountDao.closeAccount(accountId);
    }

    public static void updateBalance(Long accountId, BigDecimal newBalance) {
        accountDao.updateBalance(accountId, newBalance);
    }

    public static List<Account> getProcessingCreditRequests() {
        return accountDao.getProcessingCreditRequests();
    }

    public static void changeCreditApprove(Long accountId, AccountStatus status, Long approverId) throws Exception {
        Profile approver = ProfileService.getProfileById(approverId);

        if (!Objects.equals(approver.getRole(), ProfileRole.Admin.toSqlName()) && !Objects.equals(approver.getRole(), ProfileRole.SuperAdmin.toSqlName())) {
            throw new Exception("User is not an admin");
        }

        Account account = accountDao.getById(accountId);

        if (!Objects.equals(account.getType(), AccountType.Credit.toSqlName())) {
            throw new Exception("Account is not credit account");
        }

        if (!Objects.equals(account.getStatus(), AccountStatus.Processing.toSqlName())) {
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
