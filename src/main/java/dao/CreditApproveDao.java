package dao;

import models.CreditApprove;

public interface CreditApproveDao {
    void create(CreditApprove ca);

    CreditApprove getByAccountId(Long accountId);
}
