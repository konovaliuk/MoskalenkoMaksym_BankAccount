package dao;

import models.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountDao {
    void create(Account a);

    void openAccount(Long id);

    void closeAccount(Long id);

    Account getById(Long id);

    Account getByAccountNumber(String accountNumber);

    List<Account> getByProfileId(Long profileId);

    Account getDefaultAccountByProfileId(Long profileId);

    List<Account> getProcessingCreditRequests();

    void updateBalance(Long id, BigDecimal newBalance);

    void update(Account a);
}
