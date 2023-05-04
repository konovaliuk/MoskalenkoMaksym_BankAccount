package main.java.dao;

import main.java.models.AccountRate;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountRateDao {
    void create(AccountRate ar);

    AccountRate getByAccountId(UUID accountId);
}
