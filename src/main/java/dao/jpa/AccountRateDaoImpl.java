package dao.jpa;

import dao.AccountRateDao;
import jakarta.persistence.EntityManager;
import models.AccountRate;

public class AccountRateDaoImpl implements AccountRateDao {
    private final EntityManager entityManager;

    public AccountRateDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(AccountRate ar) {
        entityManager.persist(ar);
    }

    @Override
    public AccountRate getByAccountId(Long accountId) {
        return entityManager.find(AccountRate.class, accountId);
    }

}
