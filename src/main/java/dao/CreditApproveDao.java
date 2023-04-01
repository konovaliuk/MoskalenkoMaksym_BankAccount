package main.java.dao;

import main.java.models.CreditApprove;

import java.util.List;
import java.util.UUID;

public interface CreditApproveDao {
    void create(CreditApprove ca);

    CreditApprove getByAccountId(UUID accountId);

    List<CreditApprove> getByApproverId(UUID approverId);
}
