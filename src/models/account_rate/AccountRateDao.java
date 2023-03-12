package models.account_rate;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountRateDao {
    void create(AccountRate ar);

    void updateCumulativeBalance(UUID accountId, BigDecimal newBalance);

    AccountRate getByAccountId(UUID accountId);
}
