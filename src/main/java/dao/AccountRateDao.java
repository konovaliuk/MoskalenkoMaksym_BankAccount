package dao;

import models.AccountRate;


public interface AccountRateDao {
    void create(AccountRate ar);

    AccountRate getByAccountId(Long accountId);
}
