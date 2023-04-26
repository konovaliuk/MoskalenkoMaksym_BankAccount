package main.java.models;

import main.java.types.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Transaction {
    private UUID id;
    private UUID sourceAccount;
    private UUID destinationAccount;
    private BigDecimal volume;
    private TransactionType type;
    private Timestamp executedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Transaction() {
    }

    public Transaction(UUID sourceAccount, UUID destinationAccount, BigDecimal volume, TransactionType type, Timestamp executedAt) {
        this.id = UUID.randomUUID();
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.volume = volume;
        this.type = type;
        this.executedAt = executedAt;
    }

    public Transaction(UUID id, UUID sourceAccount, UUID destinationAccount, BigDecimal volume,
                       TransactionType type, Timestamp executedAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.volume = volume;
        this.type = type;
        this.executedAt = executedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public UUID getId() {
        return id;
    }

    public UUID getSourceAccount() {
        return sourceAccount;
    }

    public UUID getDestinationAccount() {
        return destinationAccount;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public TransactionType getType() {
        return type;
    }

    public Timestamp getExecutedAt() {
        return executedAt;
    }
}

