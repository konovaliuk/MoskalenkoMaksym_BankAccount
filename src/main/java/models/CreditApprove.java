package main.java.models;

import java.util.UUID;


public class CreditApprove {
    private UUID accountId;
    private UUID approverId;

    public CreditApprove() {
    }

    public CreditApprove(UUID accountId, UUID approverId) {
        this.accountId = accountId;
        this.approverId = approverId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public UUID getApproverId() {
        return approverId;
    }
}