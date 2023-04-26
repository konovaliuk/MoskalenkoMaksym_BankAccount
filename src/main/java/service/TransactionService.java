package main.java.service;

import main.java.dao.TransactionDao;
import main.java.dao.factory.DaoFactory;
import main.java.models.Account;
import main.java.models.Transaction;
import main.java.types.AccountType;
import main.java.types.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private static final TransactionDao transactionDao = DaoFactory.getTransactionDao();

    public static List<Transaction> getTransactionsByAccountId(UUID accountId) {
        return transactionDao.getByAccountId(accountId);
    }

    public static void createTransaction(UUID sourceAccount, UUID destinationAccount, BigDecimal amount, TransactionType type) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        Transaction tx = new Transaction(sourceAccount, destinationAccount, amount, type, now);

        transactionDao.create(tx);
    }

    public static void handleTransactionCreation(UUID sourceAccountId, Boolean isSourceExternal, UUID destinationAccountId, Boolean isDestinationExternal, BigDecimal amount, UUID profileId) throws Exception {
        Account sourceAccount = null;
        Account destinationAccount = null;

        if (amount.compareTo(new BigDecimal(0)) <= 0) {
            throw new Exception("Amount should be a positive number");
        }

        if (isDestinationExternal && isSourceExternal) {
            throw new Exception("Cannot transfer between two external addresses");
        }

        if (destinationAccountId == sourceAccountId) {
            throw new Exception("Cannot transfer between the same account");
        }

        if (!isSourceExternal) {
            sourceAccount = AccountsService.validateAccountForTransaction(sourceAccountId);
            if (sourceAccount.getBalance().compareTo(amount) < 0) {
                throw new Exception("Insufficient balance");
            }

            if (sourceAccount.getProfileId().compareTo(profileId) != 0) {
                throw new Exception("Account does not belong to user");
            }

            if (sourceAccount.getType() == AccountType.Credit) {
                throw new Exception("Wrong account type");
            }
        }

        if (!isDestinationExternal) {
            destinationAccount = AccountsService.validateAccountForTransaction(destinationAccountId);

            if (destinationAccount.getType() == AccountType.Debit) {
                throw new Exception("Wrong account type");
            }
        }

        if (sourceAccount != null && sourceAccount.getType() == AccountType.Debit) {
            if (sourceAccount.getBalance().compareTo(amount) != 0) {
                throw new Exception("Funds from debit account should be withdraw all at once");
            }
            AccountsService.closeAccount(sourceAccountId);
        }

        if (destinationAccount != null && destinationAccount.getType() == AccountType.Credit) {
            if (destinationAccount.getBalance().negate().compareTo(amount) < 0) {
                throw new Exception("Debt on credit account is lower");
            }
            if (destinationAccount.getBalance().negate().compareTo(amount) == 0) {
                AccountsService.closeAccount(destinationAccountId);
            }
        }

        if (isSourceExternal) {
            createTransaction(null, destinationAccountId, amount, TransactionType.Deposit);
            AccountsService.updateBalance(destinationAccountId, destinationAccount.getBalance().add(amount));
        } else if (isDestinationExternal) {
            createTransaction(sourceAccountId, null, amount, TransactionType.Withdraw);
            AccountsService.updateBalance(sourceAccountId, sourceAccount.getBalance().subtract(amount));
        } else {
            createTransaction(sourceAccountId, destinationAccountId, amount, TransactionType.Transfer);
            AccountsService.updateBalance(destinationAccountId, destinationAccount.getBalance().add(amount));
            AccountsService.updateBalance(sourceAccountId, sourceAccount.getBalance().subtract(amount));
        }
    }
}
