package main.java.models;

import main.java.types.AccountStatus;
import main.java.types.AccountType;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.UUID;

public class Account {
    private UUID id;
    private UUID profileId;

    private AccountType type;
    private BigDecimal balance;
    private AccountStatus status;
    private Time expiredAt;
    private Time openedAt;
    private Time closedAt;
    private Time createdAt;
    private Time updatedAt;

    public Account() {
    }

    public Account(
            UUID id,
            UUID profileId,
            AccountType type,
            BigDecimal balance,
            AccountStatus status,
            Time expiredAt,
            Time openedAt,
            Time closedAt
    ) {
        this.id = id;
        this.profileId = profileId;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.expiredAt = expiredAt;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProfileId() {
        return profileId;
    }


    public AccountType getType() {
        return type;
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public AccountStatus getStatus() {
        return status;
    }

    public Time getExpiredAt() {
        return expiredAt;
    }


    public Time getOpenedAt() {
        return openedAt;
    }

    public Time getClosedAt() {
        return closedAt;
    }
}
