package main.java.models;

import main.java.types.AccountStatus;
import main.java.types.AccountType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Account {
    private UUID id;
    private UUID profileId;

    private AccountType type;
    private BigDecimal balance;
    private AccountStatus status;
    private Timestamp expiredAt;
    private Timestamp openedAt;
    private Timestamp closedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Account() {
    }

    public Account(UUID profileId, AccountType type, BigDecimal balance) {
        this.id = UUID.randomUUID();
        this.profileId = profileId;
        this.type = type;
        this.balance = balance;
    }

    public Account(
            UUID id,
            UUID profileId,
            AccountType type,
            BigDecimal balance,
            AccountStatus status,
            Timestamp expiredAt,
            Timestamp openedAt,
            Timestamp closedAt
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

    public Timestamp getExpiredAt() {
        return expiredAt;
    }


    public Timestamp getOpenedAt() {
        return openedAt;
    }

    public Timestamp getClosedAt() {
        return closedAt;
    }

    public void setStatus(AccountStatus s) {
        this.status = s;
    }

    public void setOpenedAt(Timestamp t) {
        this.openedAt = t;
    }

    public void setExpiredAt(Timestamp t) {
        this.expiredAt = t;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
