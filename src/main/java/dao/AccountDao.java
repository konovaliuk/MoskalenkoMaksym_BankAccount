package main.java.dao;

import main.java.models.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountDao {
    void create(Account a);

    void openAccount(UUID id);

    void closeAccount(UUID id);

    Account getById(UUID id);

    List<Account> getByProfileId(UUID profileId);

    Account getDefaultAccountByProfileId(UUID profileId);

    List<Account> getProcessingCreditRequests();

    void updateBalance(UUID id, BigDecimal newBalance);
}
