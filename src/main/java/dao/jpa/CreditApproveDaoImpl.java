package dao.jpa;

import dao.CreditApproveDao;
import jakarta.persistence.EntityManager;
import models.CreditApprove;

public class CreditApproveDaoImpl implements CreditApproveDao {
    private final EntityManager entityManager;

    public CreditApproveDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(CreditApprove ca) {
        entityManager.persist(ca);
    }

    @Override
    public CreditApprove getByAccountId(Long accountId) {
        return entityManager.find(CreditApprove.class, accountId);
    }
}
