package models.account_rate;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountRate {
    private UUID accountId;
    private BigDecimal cumulativeBalance;
    private BigDecimal rate;
    private Boolean isComplexRate;

    public AccountRate() {
    }

    public AccountRate(
            UUID accountId,
            BigDecimal cumulativeBalance,
            BigDecimal rate,
            Boolean isComplexRate
    ) {
        this.accountId = accountId;
        this.cumulativeBalance = cumulativeBalance;
        this.rate = rate;
        this.isComplexRate = isComplexRate;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getCumulativeBalance() {
        return cumulativeBalance;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Boolean getIsComplexRate() {
        return isComplexRate;
    }
}
