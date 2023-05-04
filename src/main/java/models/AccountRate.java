package main.java.models;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

public class AccountRate {
    private UUID accountId;
    private BigDecimal rate;
    private BigDecimal initialBalance;
    private Integer paymentFrequency;
    private Integer paymentsProcessed;
    private Timestamp nextPaymentAt;


    public AccountRate() {
    }

    public AccountRate(
            UUID accountId,
            BigDecimal rate,
            BigDecimal initialBalance,
            Integer paymentFrequency,
            Integer paymentsProcessed,
            Timestamp nextPaymentAt
    ) {
        this.accountId = accountId;
        this.rate = rate;
        this.initialBalance = initialBalance;
        this.paymentFrequency = paymentFrequency;
        this.paymentsProcessed = paymentsProcessed;
        this.nextPaymentAt = nextPaymentAt;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public Integer getPaymentFrequency() {
        return paymentFrequency;
    }

    public Integer getPaymentsProcessed() {
        return paymentsProcessed;
    }

    public Timestamp getNextPaymentAt() {
        return nextPaymentAt;
    }
}
